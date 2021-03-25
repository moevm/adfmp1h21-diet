package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(userViewModel)
                }
            }
        }
    }
}
enum class Sex {
    Man,
    Woman,
    Undefined
}
class UserViewModel : ViewModel() {

    private var _sex = MutableLiveData(Sex.Man)
    val sex: LiveData<Sex> = _sex

    fun onSexChange(newSex: Sex) {
        _sex.value = newSex
    }
}

@Composable
fun Greeting(userViewModel: UserViewModel) {
    val sexDialogState = remember { mutableStateOf(false)}

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sex")
            Text(text =  if (userViewModel.sex.value == Sex.Undefined) "Not set" else userViewModel.sex.value.toString())
            Button(onClick = { sexDialogState.value = true}) {
                Text(text = "Change")
            }
        }
    }

    SexDialog(
        show = sexDialogState.value,
        onSubmitButtonClick = { userViewModel.onSexChange(if (it == 1) Sex.Man else Sex.Woman) },
        onDismissRequest = { sexDialogState.value = false }
    )
}

@Composable
fun SingleSelectDialog(title: String,
                       optionsList: List<String>,
                       defaultSelected: Int,
                       submitButtonText: String,
                       onSubmitButtonClick: (Int) -> Unit,
                       onDismissRequest: () -> Unit
) {
    val selectedOption = mutableStateOf(defaultSelected)

    Dialog(
        onDismissRequest = {onDismissRequest.invoke()}
    ) {
        Surface(modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {

                Text(text = title)

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn{
                    items(items = optionsList, itemContent = {item ->
                        RadioButton(text = item, optionsList[selectedOption.value]) {
                                selectedValue -> selectedOption.value = optionsList.indexOf(selectedValue)
                        }
                    })
                }
                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    onSubmitButtonClick.invoke(selectedOption.value)
                    onDismissRequest.invoke()
                },
                    shape = MaterialTheme.shapes.medium) {
                    Text(text = submitButtonText)
                }
            }

        }
    }
}

@Composable
fun RadioButton(text: String, selectedValue: String, onClickListener: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = (text == selectedValue),
                onClick = {
                    onClickListener(text)
                }
            )
            .padding(horizontal = 16.dp)
    ) {
        androidx.compose.material.RadioButton(
            selected = (text == selectedValue),
            onClick = {
                onClickListener(text)
            }
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1.merge(),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun SexDialog(
    show: Boolean, onSubmitButtonClick: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    if (show) {
        SingleSelectDialog(
            title = "Setting sex",
            optionsList = listOf("Woman", "Man"),
            defaultSelected = 1,
            submitButtonText = "Submit",
            onSubmitButtonClick = onSubmitButtonClick,
            onDismissRequest = onDismissRequest
        )
    }
}