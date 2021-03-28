package com.example.composediet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo

@ExperimentalComposeUiApi
@Composable
fun DishDetailsScreen(navController: NavHostController, foodViewModel: FoodViewModel, prop:String?) {
    val toastText = rememberSaveable { mutableStateOf("")}
    val dismiss = {
        foodViewModel.onDishSelectedChange(null)
        navController.navigate(Screen.Food.route) {
            popUpTo(Screen.Profile.route) {}
        }
    }

    DishDialog(
        onDelete = {
            foodViewModel.removeDish(it)
            dismiss()
        },
        onCreate = {
            if (it.name.value.toString() == "") {
                toastText.value = "Name must not be empty!"
            }
            else if (foodViewModel.foodItemExists(it.name.value.toString())) {
                toastText.value = "There is product with such name!"
            }
            else if (foodViewModel.dishExists(it.name.value.toString())) {
                toastText.value = "There is dish with such name!"
            }
            else {
                foodViewModel.addDish(it)
                dismiss()
            }
        },
        onEat = {
            dismiss()
        },
        onSelectIngredients = {}
        ,
        prop = prop,
        dish = if (foodViewModel.dishSelected.value != null) foodViewModel.dishSelected.value!! else DishViewModel()
    )

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

@ExperimentalComposeUiApi
@Composable
private fun DishDialog(
    onDelete: (DishViewModel) -> Unit = {},
    onEat: (DishViewModel) -> Unit = {},
    onCreate: (DishViewModel) -> Unit = {},
    onSelectIngredients: (DishViewModel) -> Unit = {},
    prop: String?,
    dish: DishViewModel
) {
    val (name, setName) = rememberSaveable { mutableStateOf(dish.name.value.toString()) }
    val ingredients: List<FoodItemViewModel> by dish.ingredients.observeAsState(listOf())

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    if (prop == "review") {
        coroutineScope.launch {
            listState.animateScrollToItem(8)
        }
    }
    LazyColumn(state = listState) {
        item {
            TextInput(
                name = "Name",
                value = name,
                onValueChange = {
                    setName(it)
                    dish.onNameChange(it)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        items(items = dish.ingredients.value!!) {foodItem ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = foodItem.name.value.toString())
                Text(text = "${ foodItem.kilocalories.value } kcal")
            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(bottom = 8.dp)
                    .padding(end = 24.dp)
                    .padding(start = 24.dp)
            ) {
                Text(
                    text = "Total", fontFamily = FontFamily.Cursive,
                    fontSize = 24.sp,
                )
                Text(
                    text = "${ingredients.sumOf {it.kilocalories.value!!.toInt()}} kcal",
                    fontSize = 24.sp
                )
            }

        }
        when(prop) {
            "create" -> {
                item {
                    Button(
                        onClick = { onSelectIngredients(dish) },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(text = "Select ingredients")
                    }
                    Button(
                        onClick = {
                            onCreate(dish)
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(text = "Create dish")
                    }
                }
            }
            "review" -> {
                item {
                    Button(
                        onClick = { onDelete(dish) },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(text = "Delete")
                    }
                }
                item {
                    Button(
                        onClick = { onEat(dish) },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(56.dp)

                    ) {
                        Text(text = "Eat it!")
                    }
                }
            }
        }
    }
}