package com.example.composediet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.composediet.ui.theme.ComposeDietTheme

class MainActivity : ComponentActivity() {
    val foodViewModel by viewModels<FoodViewModel>()
    val profileViewModel by viewModels<ProfileViewModel>()
    val waterViewModel by viewModels<WaterViewModel>()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDietTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Router(foodViewModel,profileViewModel, waterViewModel)
                }
            }
        }
    }
}