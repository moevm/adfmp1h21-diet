package com.example.composediet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Toast(show: Boolean, modifier: Modifier,  text: String, onClose: () -> Unit) {
    if (show) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier,
        ) {
            Snackbar(action = {
                Button(onClick =  onClose) {
                    Text(text = "Got it!")
                }
            })
            {
                Text(text = text)
            }
        }
    }
}
