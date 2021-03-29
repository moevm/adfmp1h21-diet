package com.example.composediet

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.composediet.calendar.CalendarViewModel
import com.example.composediet.ui.theme.ComposeDietTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoreApplication: Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val foodViewModel by viewModels<FoodViewModel>()
    val profileViewModel by viewModels<ProfileViewModel>()
    val waterViewModel by viewModels<WaterViewModel>()
    val calendarViewModel by viewModels<CalendarViewModel>()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDietTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Router(foodViewModel,profileViewModel, waterViewModel, calendarViewModel)
                }
            }
        }
    }
}