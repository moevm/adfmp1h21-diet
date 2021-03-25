package com.example.composediet

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

sealed class Screen(val route: String) {
    object Water : Screen("water")
    object Food : Screen("food")
    object Profile : Screen("profile")
    object Calendar : Screen("calendar")
}

@Composable
fun MainView(foodViewModel: FoodViewModel, profileViewModel: ProfileViewModel) {
    val bottomNavItems = listOf(
        Pair(Screen.Water, R.drawable.glass),
        Pair(Screen.Food, R.drawable.food),
        Pair(Screen.Profile, R.drawable.profile)
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

    Scaffold(
        bottomBar = {
            BottomNavigation {
                bottomNavItems.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.second),
                                contentDescription = null
                            )
                        },
                        selected = false,
                        onClick = {
                            navController.navigate(item.first.route) {
                                popUpTo = navController.graph.startDestination
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        },
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "March 2021",
                    modifier = Modifier.padding(2.dp),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Week 1",
                        modifier = Modifier.padding(2.dp)
                    )
                    Spacer(Modifier.size(2.dp))
                    IconButton(
                        onClick = { navController.navigate(Screen.Calendar.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    ) {
        NavHost(navController, startDestination = Screen.Profile.route) {
            composable(Screen.Water.route) { WaterScreen() }
            composable(Screen.Food.route) {
                val items: List<FoodItem> by foodViewModel.foodItems.observeAsState(listOf())
                FoodScreen(
                    items = items,
                    onAddItem = { foodViewModel.addItem(it) },
                    onRemoveItem = { foodViewModel.removeItem(it) }
                )
            }
            composable(Screen.Profile.route) { ProfileScreen(profileViewModel) }
            composable(Screen.Calendar.route) { CalendarScreen() }
        }
    }
}