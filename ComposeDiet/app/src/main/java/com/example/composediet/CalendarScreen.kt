package com.example.composediet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun CalendarScreen(navController: NavHostController) {
    Navigation(navController = navController) {
        Text("Calendar")
    }
}