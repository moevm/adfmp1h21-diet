package com.example.composediet

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.mutableStateOf
import androidx.compose.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState

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