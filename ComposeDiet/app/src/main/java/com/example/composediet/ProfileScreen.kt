package com.example.composediet

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalComposeUiApi
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = ProfileViewModel()) {
    val sexDialogState = remember { mutableStateOf(false) }
    val dietDialogState = remember { mutableStateOf(false) }

    val height: Int by profileViewModel.height.observeAsState(-1)
    val weight: Int by profileViewModel.weight.observeAsState(-1)
    val age: Int by profileViewModel.age.observeAsState(-1)

    var diet: String = ""

    when (profileViewModel.diet.value) {
        Diet.LoseWeight -> diet = "Losing weight"
        Diet.GetFat -> diet = "Getting fatter"
        Diet.HoldWeight -> diet = "Holding weight"
        Diet.Undefined -> diet = "Not set"
    }
    
    LazyColumn(state = rememberLazyListState()) {
        item {
            UnsignedIntInput(
                name = "Height",
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                value = height,
                onValueChange = { profileViewModel.onHeightChange(it.toIntOrNull() ?: height) }
            )
        }
        item {
            UnsignedIntInput(
                name = "Weight",
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                value = weight,
                onValueChange = { profileViewModel.onWeightChange(it.toIntOrNull() ?: weight) }
            )
        }
        item {
            UnsignedIntInput(
                name = "Age",
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                value = age,
                onValueChange = { profileViewModel.onAgeChange(it.toIntOrNull() ?: age) }
            )
        }
        item {
                Row(modifier = Modifier.padding(top = 8.dp).padding(bottom = 8.dp).padding(start = 24.dp)) {
                    Column(modifier = Modifier.defaultMinSize(minWidth = 150.dp)) {
                        Text(
                            text = "Sex",
                            style = typography.h6.copy(fontSize = 16.sp),
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = if (profileViewModel.sex.value == Sex.Undefined) "Not set" else profileViewModel.sex.value.toString(),
                            style = typography.subtitle2,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Button(modifier = Modifier.fillMaxHeight(), onClick = { sexDialogState.value = true }) {
                        Text(text = "Change")
                    }
                }
        }
        item {
            Row(modifier = Modifier.padding(top = 8.dp).padding(bottom = 8.dp).padding(start = 24.dp)) {
                Column(modifier = Modifier.defaultMinSize(minWidth = 150.dp)) {
                    Text(
                        text = "Diet",
                        style = typography.h6.copy(fontSize = 16.sp),
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = diet,
                        style = typography.subtitle2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Button(onClick = { dietDialogState.value = true }) {
                    Text(text = "Change")
                }
            }
        }
        item {
            Row(modifier = Modifier.padding(top = 8.dp).padding(bottom = 8.dp).padding(start = 24.dp)) {
                Column(modifier = Modifier.defaultMinSize(minWidth = 150.dp)) {
                    Text(
                        text = "Diet begin",
                        style = typography.h6.copy(fontSize = 16.sp),
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = profileViewModel.dateBegin.value.toString(),
                        style = typography.subtitle2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        item {
            Row(modifier = Modifier.padding(top = 8.dp).padding(bottom = 8.dp).padding(start = 24.dp)) {
                Column(modifier = Modifier.defaultMinSize(minWidth = 150.dp)) {
                    Text(
                        text = "Diet end",
                        style = typography.h6.copy(fontSize = 16.sp),
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = profileViewModel.dateEnd.value.toString(),
                        style = typography.subtitle2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
    SexDialog(
        show = sexDialogState.value,
        onSubmitButtonClick = { profileViewModel.onSexChange(if (it == 1) Sex.Man else Sex.Woman) },
        onDismissRequest = { sexDialogState.value = false }
    )
    DietDialog(
        show = dietDialogState.value,
        onSubmitButtonClick = {
            when (it) {
                0 -> profileViewModel.onDietChange(Diet.LoseWeight)
                1 -> profileViewModel.onDietChange(Diet.GetFat)
                2 -> profileViewModel.onDietChange(Diet.HoldWeight)
            }
        },
        onDismissRequest = { dietDialogState.value = false }
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
@Composable
fun DietDialog(
    show: Boolean, onSubmitButtonClick: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    if (show) {
        SingleSelectDialog(
            title = "Setting diet",
            optionsList = listOf("Losing weight", "Getting fatter", "Holding weight"),
            defaultSelected = 0,
            submitButtonText = "Submit",
            onSubmitButtonClick = onSubmitButtonClick,
            onDismissRequest = onDismissRequest
        )
    }
}