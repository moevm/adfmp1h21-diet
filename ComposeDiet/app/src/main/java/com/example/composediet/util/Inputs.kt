package com.example.composediet

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@ExperimentalComposeUiApi
@Composable
fun UnsignedIntInput(name: String, value: Int, onValueChange: (Int) -> Unit, modifier: Modifier) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = if (value == -1) "" else value.toString(),
        modifier = modifier,
        label = { Text(text = name) },
        onValueChange = { onValueChange(it.toIntOrNull() ?: value) },
        placeholder = { Text(text = "") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number).copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hideSoftwareKeyboard() })
    )
}

@ExperimentalComposeUiApi
@Composable
fun TextInput(name: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = value,
        modifier = modifier,
        label = { Text(text = name) },
        onValueChange = onValueChange,
        placeholder = { Text(text = "") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text).copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hideSoftwareKeyboard() })
    )
}