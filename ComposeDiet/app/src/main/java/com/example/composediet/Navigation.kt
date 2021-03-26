package com.example.composediet

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate

@Composable
fun Navigation(navController: NavHostController, content: @Composable ColumnScope.() -> Unit) {
    val bottomNavItems = listOf(
        Pair(Screen.Water, R.drawable.glass),
        Pair(Screen.Food, R.drawable.food),
        Pair(Screen.Profile, R.drawable.profile)
    )

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
        ColumnScope.content()
    }
}