package com.example.composediet

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel) {
    val sexDialogState = remember { mutableStateOf(false) }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Height")
            TextField(
                value = if (profileViewModel.height.value == -1) "" else profileViewModel.height.value.toString(),
                onValueChange = { profileViewModel.onHeightChange(if (it == "") -1 else it.toInt()) }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Weight")
            TextField(
                value = if (profileViewModel.weight.value == -1) "" else profileViewModel.weight.value.toString(),
                onValueChange = { profileViewModel.onWeightChange(if (it == "") -1 else it.toInt()) }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Age")
            TextField(
                value = if (profileViewModel.age.value == -1) "" else profileViewModel.age.value.toString(),
                onValueChange = { profileViewModel.onAgeChange(if (it == "") -1 else it.toInt()) }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sex")
            Text(text = if (profileViewModel.sex.value == Sex.Undefined) "Not set" else profileViewModel.sex.value.toString())
            Button(onClick = { sexDialogState.value = true }) {
                Text(text = "Change")
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Diet")
            Text(text = if (profileViewModel.diet.value == Diet.Undefined) "Not set" else profileViewModel.diet.value.toString())
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Diet begin")
            Text(text = profileViewModel.dateBegin.value.toString())
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Diet end")
            Text(text = profileViewModel.dateEnd.value.toString())
        }
    }
    SexDialog(
        show = sexDialogState.value,
        onSubmitButtonClick = { profileViewModel.onSexChange(if (it == 1) Sex.Man else Sex.Woman) },
        onDismissRequest = { sexDialogState.value = false }
    )
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