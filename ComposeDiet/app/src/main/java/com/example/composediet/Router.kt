package com.example.composediet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.composediet.calendar.CalendarViewModel
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.composediet.viewmodels.FoodHistoryViewModel
import com.example.composediet.viewmodels.FoodViewModel
import com.example.composediet.viewmodels.ProfileViewModel
import java.time.LocalDate

sealed class Screen(val route: String) {
    object Water : Screen("water")
    object Food : Screen("food")
    object Profile : Screen("profile")
    object Calendar : Screen("calendar")
    object ProductDetails : Screen("product/{prop}")
    object DishDetails : Screen("dish/{prop}")
    object ProductsSelection : Screen("products-selection")
    object DayFoodHistory : Screen("day-food")
    object Home : Screen("home")
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun Router(foodViewModel: FoodViewModel,
           profileViewModel: ProfileViewModel,
           calendarViewModel: CalendarViewModel,
           foodHistoryViewModel: FoodHistoryViewModel
) {
    val navController = rememberNavController()

    val daySelected by calendarViewModel.daySelected.observeAsState(LocalDate.now())

    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                profileViewModel= profileViewModel,
                navController = navController
            )
        }
        composable(Screen.Water.route) {
            WaterScreen(
                profileViewModel= profileViewModel,
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
                foodHistoryViewModel = foodHistoryViewModel,
                profileViewModel = profileViewModel,
                backStackEntry.arguments?.getString("prop")
            )
        }
        composable(Screen.DishDetails.route) { backStackEntry ->
            DishDetailsScreen(
                navController = navController,
                foodViewModel = foodViewModel,
                foodHistoryViewModel = foodHistoryViewModel,
                profileViewModel = profileViewModel,
                backStackEntry.arguments?.getString("prop")
            )
        }
        composable(Screen.DayFoodHistory.route) {
            DayFoodHistoryScreen(
                navController = navController,
                localDate = daySelected,
                foodHistoryViewModel = foodHistoryViewModel
            )
        }
    }
}