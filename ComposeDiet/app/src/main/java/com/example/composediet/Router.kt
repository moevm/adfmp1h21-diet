package com.example.composediet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import com.example.composediet.calendar.CalendarViewModel
import androidx.compose.ui.ExperimentalComposeUiApi

sealed class Screen(val route: String) {
    object Water : Screen("water")
    object Food : Screen("food")
    object Profile : Screen("profile")
    object Calendar : Screen("calendar")
    object ProductDetails : Screen("product/{prop}")
    object DishDetails : Screen("dish/{prop}")
    object ProductsSelection : Screen("products-selection")
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun Router(foodViewModel: FoodViewModel,
           profileViewModel: ProfileViewModel,
           waterViewModel: WaterViewModel,
           calendarViewModel: CalendarViewModel
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Profile.route) {
        composable(Screen.Water.route) {
            WaterScreen(
                waterViewModel = waterViewModel,
                navController = navController
            )
        }
        composable(Screen.Food.route) {
            FoodScreen(
                navController = navController,
                foodViewModel = foodViewModel
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                profileViewModel = profileViewModel,
                navController = navController
            )
        }
        composable(Screen.Calendar.route) {
            CalendarScreen(
                navController = navController,
                calendarViewModel = calendarViewModel
            )
        }
        composable(Screen.ProductsSelection.route) {
            ProductsSelectionScreen(
                navController = navController,
                foodViewModel = foodViewModel
            )
        }
        composable(Screen.ProductDetails.route) { backStackEntry ->
            ProductDetailsScreen(
                navController = navController,
                foodViewModel = foodViewModel,
                backStackEntry.arguments?.getString("prop")
            )
        }
        composable(Screen.DishDetails.route) { backStackEntry ->
            DishDetailsScreen(
                navController = navController,
                foodViewModel = foodViewModel,
                backStackEntry.arguments?.getString("prop")
            )
        }

    }
}