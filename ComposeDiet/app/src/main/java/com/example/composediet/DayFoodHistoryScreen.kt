package com.example.composediet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


@Composable
fun DayFoodHistoryScreen(
    navController: NavHostController,
    foodHistoryViewModel: FoodHistoryViewModel,
    localDate: LocalDate
) {
    val foodItemsAndTimeOfDay = foodHistoryViewModel.getFoodItemsByDay(localDate)
    val dishAndTimeOfDay = foodHistoryViewModel.getDishesByDay(localDate)

    val size = foodItemsAndTimeOfDay.size + dishAndTimeOfDay.size
    var i = 0 // foodItems
    var j = 0 // dishes

    LazyColumn {
        while (i + j < size) {
            if (j >= dishAndTimeOfDay.size) {
                val foodItemViewModel = foodItemsAndTimeOfDay[i].second
                val dateTime = foodItemsAndTimeOfDay[i].first.toLocalTime()
                item {
                    FoodItemDayRow(
                        dateTime = dateTime,
                        foodItemViewModel = foodItemViewModel
                    )
                }
                i++
            }
            else if (i >= foodItemsAndTimeOfDay.size) {
                val foodItemViewModel = dishAndTimeOfDay[j].second
                val dateTime = dishAndTimeOfDay[j].first.toLocalTime()
                item {
                    FoodItemDayRow(
                        dateTime = dateTime,
                        foodItemViewModel = foodItemViewModel
                    )
                }
                j++
            }
            else if (foodItemsAndTimeOfDay[i].first < dishAndTimeOfDay[j].first) {
                val foodItemViewModel = foodItemsAndTimeOfDay[i].second
                val dateTime = foodItemsAndTimeOfDay[i].first.toLocalTime()
                item {
                    FoodItemDayRow(
                        dateTime = dateTime,
                        foodItemViewModel = foodItemViewModel
                    )
                }
                i++
            }
            else {
                val foodItemViewModel = dishAndTimeOfDay[j].second
                val dateTime = dishAndTimeOfDay[j].first.toLocalTime()
                item {
                    FoodItemDayRow(
                        dateTime = dateTime,
                        foodItemViewModel = foodItemViewModel
                    )
                }
                j++
            }
        }
    }
}

@Composable
fun FoodItemDayRow(
    foodItemViewModel: FoodItemViewModel,
    modifier: Modifier = Modifier,
    dateTime: LocalTime,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.defaultMinSize(minWidth = 100.dp)) {
            Text(
                text = "food",
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = foodItemViewModel.name.value.toString(),
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                color = MaterialTheme.colors.onSurface
            )
        }
        Column(modifier = Modifier.defaultMinSize(minWidth = 100.dp)) {
            val fullLocalTime = dateTime.toString()

            Text(
                text = "time",
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "${ fullLocalTime.subSequence(0, fullLocalTime.length - 4)}",
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Column(modifier = Modifier.defaultMinSize(minWidth = 100.dp)) {
            Text(
                text = "amount",
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "${foodItemViewModel.num.value}",
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}