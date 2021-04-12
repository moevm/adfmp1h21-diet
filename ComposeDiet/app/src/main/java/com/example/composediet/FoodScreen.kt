package com.example.composediet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.navigate
import com.example.composediet.data.Product
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.composediet.data.Dish
import com.example.composediet.model.DishSelectedViewModel
import com.example.composediet.model.FoodViewModel
import com.example.composediet.model.ProductSelectedViewModel

@Composable

fun FoodRow(name: String, proteins: Short, fats: Short, carbohydrates: Short, water: Short, calories: Short, onItemClicked: () -> Unit, modifier: Modifier = Modifier, index: Int){
    var newColor: Color
    if (index % 2 == 0) {
        newColor = Color(0xFF9999FF)
    }
    else {
        newColor = Color(0xFF99CCFF)
    }
    Row(
        modifier = modifier
            .clickable{onItemClicked()}
            .fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(modifier = Modifier.fillMaxWidth(), color = newColor) {
            Column(modifier = Modifier
                .defaultMinSize(minWidth = 150.dp)
                .padding(8.dp)) {

                    Text(
                        text = name,
                        style = MaterialTheme.typography.h6.copy(fontSize = 16.sp,
                            fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif),
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = "$proteins g, " +
                                "$fats g, " +
                                "$carbohydrates g, " +
                                "$water l, " +
                                "$calories kcal",
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
    foodViewModel: FoodViewModel,
    productSelectedViewModel: ProductSelectedViewModel,
    dishSelectedViewModel: DishSelectedViewModel
) {
    val toolButtonsVisibleState = rememberSaveable { mutableStateOf(false) }
    val toastText = rememberSaveable { mutableStateOf("")}
    val products: List<Product> by foodViewModel.products.observeAsState(listOf())
    val dishes: List<Dish> by foodViewModel.dishes.observeAsState(listOf())
    var i: Int = 0

    Box {
        Navigation(navController = navController) {
            Column {
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
                        items(items = dishes.toList()) { dish ->
                            FoodRow(
                                name = dish.name,
                                proteins = dish.proteins,
                                fats = dish.fats,
                                carbohydrates = dish.carbohydrates,
                                water = dish.water,
                                calories = dish.calories,
                                onItemClicked = {
                                    dishSelectedViewModel.onSelectedChange(dish)
                                    toolButtonsVisibleState.value = false
                                    navController.navigate("dish/review")
                                },
                                index = i
                            )
                            i += 1
                        }
                        items(items = products) { product ->
                            FoodRow(
                                name = product.name,
                                proteins = product.proteins,
                                fats = product.fats,
                                carbohydrates = product.carbohydrates,
                                water = product.water,
                                calories = product.calories,
                                onItemClicked = {
                                    productSelectedViewModel.onSelectedChange(product)
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
                                    productSelectedViewModel.onSelectedChange(null)
                                    toolButtonsVisibleState.value = false
                                    navController.navigate("product/create")
                                },
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(text = "Product")
                            }
                            Button(
                                onClick = {
                                    if (foodViewModel.products.value!!.isEmpty()) {
                                        toastText.value =
                                            "At least one product must exist to create dish!"
                                    } else {
                                        dishSelectedViewModel.onSelectedChange(null)
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
