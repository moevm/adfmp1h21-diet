package com.example.composediet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.navigate
import com.example.composediet.viewmodels.DishViewModel
import com.example.composediet.viewmodels.FoodItemViewModel
import com.example.composediet.viewmodels.FoodViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FoodSearchInput(onItemComplete: (FoodItemViewModel) -> Unit) {
//    val (text, setText) = remember { mutableStateOf("") }
//    val submit = {
//        onItemComplete(FoodItem(text))
//        setText("")
//    }
//    Column {
//        Row(
//            Modifier
//                .padding(horizontal = 16.dp)
//                .padding(top = 16.dp)
//        ) {
//            val keyboardController = LocalSoftwareKeyboardController.current
//            TextField(
//                value = text,
//                onValueChange = setText,
//                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
//                maxLines = 1,
//                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
//                keyboardActions = KeyboardActions(onDone = {
//                    submit()
//                    keyboardController?.hideSoftwareKeyboard()
//                }),
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 8.dp),
//            )
//        }
//    }
}

@Composable
fun FoodItemRow(foodItemViewModel: FoodItemViewModel, onItemClicked: (FoodItemViewModel) -> Unit, modifier: Modifier = Modifier, index: Int){
    var newColor: Color
    if (index % 2 == 0) {
        newColor = Color(0xFF9999FF)
    }
    else {
        newColor = Color(0xFF99CCFF)
    }
    Row(
        modifier = modifier
            .clickable { onItemClicked(foodItemViewModel) }
            //.padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(modifier = Modifier.fillMaxWidth(), color = newColor) {
            Column(modifier = Modifier.defaultMinSize(minWidth = 150.dp).padding(8.dp)) {

                    Text(
                        text = foodItemViewModel.name.value.toString(),
                        style = MaterialTheme.typography.h6.copy(fontSize = 16.sp,
                            fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif),
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = "${foodItemViewModel.proteins.value} g, " +
                                "${foodItemViewModel.fats.value} g, " +
                                "${foodItemViewModel.carbohydrates.value} g, " +
                                "${foodItemViewModel.water.value} l, " +
                                "${foodItemViewModel.kilocalories.value} kcal",
                        style = MaterialTheme.typography.subtitle2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun FoodScreen(
    navController: NavHostController,
    foodViewModel: FoodViewModel
) {
    val toolButtonsVisibleState = rememberSaveable { mutableStateOf(false) }
    val toastText = rememberSaveable { mutableStateOf("")}
    val foodItems: Set<FoodItemViewModel> by foodViewModel.foodItems.observeAsState(setOf())
    val dishes: Set<DishViewModel> by foodViewModel.dishes.observeAsState(setOf())
    var i: Int = 0

    Box {
        Navigation(navController = navController) {
            Column {
                Box(modifier = Modifier.fillMaxSize()) {
                    FoodSearchInput(onItemComplete = { })
                    LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
                        items(items = dishes.toList()) { dish ->
                            FoodItemRow(
                                foodItemViewModel = dish,
                                onItemClicked = {
                                    foodViewModel.onTargetDishChange(dish)
                                    toolButtonsVisibleState.value = false
                                    navController.navigate("dish/review")
                                },
                                index = i
                            )
                            i += 1
                        }
                        items(items = foodItems.toList()) { foodItem ->
                            FoodItemRow(
                                foodItemViewModel = foodItem,
                                onItemClicked = {
                                    foodViewModel.onFoodItemSelectedChange(foodItem)
                                    toolButtonsVisibleState.value = false
                                    navController.navigate("product/review")
                                },
                                index = i
                            )
                            i += 1
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (toolButtonsVisibleState.value) {
                            Button(
                                onClick = {
                                    foodViewModel.onFoodItemSelectedChange(null)
                                    toolButtonsVisibleState.value = false
                                    navController.navigate("product/create")
                                },
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(text = "Product")
                            }
                            Button(
                                onClick = {
                                    if (foodViewModel.foodItems.value!!.isEmpty()) {
                                        toastText.value =
                                            "At least one product must exist to create dish!"
                                    } else {
                                        foodViewModel.onTargetDishChange(null)
                                        toolButtonsVisibleState.value = false
                                        navController.navigate("dish/create")
                                    }
                                },
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(text = "Dish")
                            }
                        }
                        FloatingActionButton(
                            onClick = {
                                toolButtonsVisibleState.value = !toolButtonsVisibleState.value
                            },
                            backgroundColor = Color.Red,
                            modifier = Modifier
                                .padding(bottom = 68.dp)
                                .padding(end = 12.dp)
                        ) {
                            Text("+", fontSize = 30.sp, color = Color.White)
                        }
                    }
                }
            }
        }
        Toast(
            show = toastText.value != "",
            text = toastText.value,
            onClose = { toastText.value = "" },
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .height(56.dp)
        )
    }
}
