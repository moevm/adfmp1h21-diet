package com.example.composediet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import com.example.composediet.ui.theme.ComposeDietTheme

class MainActivity : ComponentActivity() {
    val foodViewModel by viewModels<FoodViewModel>()
    val profileViewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDietTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainView(foodViewModel,profileViewModel)
                }
            }
        }
    }
}