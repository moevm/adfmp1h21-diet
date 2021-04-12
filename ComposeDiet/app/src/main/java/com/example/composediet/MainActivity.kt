package com.example.composediet

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composediet.calendar.CalendarViewModel
import com.example.composediet.model.DishSelectedViewModel
import com.example.composediet.model.FoodViewModel
import com.example.composediet.model.ProductSelectedViewModel
import com.example.composediet.ui.theme.ComposeDietTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoreApplication: Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val foodViewModel: FoodViewModel by viewModels()
    val productSelectedViewModel: ProductSelectedViewModel by viewModels()
    val dishSelectedViewModel: DishSelectedViewModel by viewModels()
    val profileViewModel by viewModels<ProfileViewModel>()
    val calendarViewModel by viewModels<CalendarViewModel>()
    val foodHistoryViewModel by viewModels<FoodHistoryViewModel>()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDietTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Router(
                        foodViewModel,
                        profileViewModel,
                        calendarViewModel,
                        foodHistoryViewModel
                    )
                }
            }
        }
    }
}