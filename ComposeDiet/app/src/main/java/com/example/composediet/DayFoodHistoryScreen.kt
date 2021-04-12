package com.example.composediet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composediet.model.ProductViewModel
import java.time.LocalDate
import java.time.LocalTime


@Composable
fun DayFoodHistoryScreen(
    navController: NavHostController,
    foodHistoryViewModel: FoodHistoryViewModel,
    localDate: LocalDate
) {
//    val foodItemsAndTimeOfDay = foodHistoryViewModel.getFoodItemsByDay(localDate)
//    val dishAndTimeOfDay = foodHistoryViewModel.getDishesByDay(localDate)
//
//    val size = foodItemsAndTimeOfDay.size + dishAndTimeOfDay.size
//    var i = 0 // foodItems
//    var j = 0 // dishes
//
//    var k: Int = 0
//
//    LazyColumn {
//        while (i + j < size) {
//            if (j >= dishAndTimeOfDay.size) {
//                val foodItemViewModel = foodItemsAndTimeOfDay[i].second
//                val dateTime = foodItemsAndTimeOfDay[i].first.toLocalTime()
//                val k1 = k
//                item {
//                    FoodItemDayRow(
//                        dateTime = dateTime,
//                        foodItemViewModel = foodItemViewModel,
//                        index = k1
//                    )
//                }
//                i++
//                k += 1
//            }
//            else if (i >= foodItemsAndTimeOfDay.size) {
//                val foodItemViewModel = dishAndTimeOfDay[j].second
//                val dateTime = dishAndTimeOfDay[j].first.toLocalTime()
//                val k1 = k
//                item {
//                    FoodItemDayRow(
//                        dateTime = dateTime,
//                        foodItemViewModel = foodItemViewModel,
//                        index = k1
//                    )
//                }
//                j++
//                k += 1
//            }
//            else if (foodItemsAndTimeOfDay[i].first < dishAndTimeOfDay[j].first) {
//                val foodItemViewModel = foodItemsAndTimeOfDay[i].second
//                val dateTime = foodItemsAndTimeOfDay[i].first.toLocalTime()
//                val k1 = k
//                item {
//                    FoodItemDayRow(
//                        dateTime = dateTime,
//                        foodItemViewModel = foodItemViewModel,
//                        index = k1
//                    )
//                }
//                i++
//                k += 1
//            }
//            else {
//                val foodItemViewModel = dishAndTimeOfDay[j].second
//                val dateTime = dishAndTimeOfDay[j].first.toLocalTime()
//                val k1 = k
//                item {
//                    FoodItemDayRow(
//                        dateTime = dateTime,
//                        foodItemViewModel = foodItemViewModel,
//                        index = k1
//                    )
//                }
//                j++
//                k += 1
//            }
//        }
//    }
}

@Composable
fun FoodItemDayRow(
    productViewModel: ProductViewModel,
    modifier: Modifier = Modifier,
    dateTime: LocalTime,
    index: Int
) {
//    var newColor: Color
//    if (index % 2 == 0) {
//        newColor = Color(0xFF9999FF)
//    }
//    else {
//        newColor = Color(0xFF99CCFF)
//    }
//    Surface(modifier = Modifier.fillMaxWidth(), color = newColor) {
//        Row(
//            modifier = modifier
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            Column(modifier = Modifier.defaultMinSize(minWidth = 100.dp)) {
//                Text(
//                    text = "food",
//                    style = typography.h6.copy(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
//                    ),
//                    color = MaterialTheme.colors.onSurface
//                )
//                Text(
//                    text = foodItemViewModel.name.value.toString(),
//                    style = typography.h6.copy(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium, fontFamily = FontFamily.Default
//                    ),
//                    color = MaterialTheme.colors.onSurface
//                )
//            }
//            Column(modifier = Modifier.defaultMinSize(minWidth = 100.dp)) {
//                val fullLocalTime = dateTime.toString()
//
//                Text(
//                    text = "time",
//                    style = MaterialTheme.typography.h6.copy(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
//                    ),
//                    color = MaterialTheme.colors.onSurface
//                )
//                Text(
//                    text = "${fullLocalTime.subSequence(0, fullLocalTime.length - 4)}",
//                    style = MaterialTheme.typography.h6.copy(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium, fontFamily = FontFamily.Default
//                    ),
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//            Column(modifier = Modifier.defaultMinSize(minWidth = 100.dp)) {
//                Text(
//                    text = "amount",
//                    style = MaterialTheme.typography.h6.copy(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
//                    ),
//                    color = MaterialTheme.colors.onSurface
//                )
//                Text(
//                    text = "${foodItemViewModel.num.value}",
//                    style = MaterialTheme.typography.h6.copy(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium, fontFamily = FontFamily.Default
//                    ),
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//            Spacer(Modifier.height(8.dp))
//        }
//    }
}