package com.example.composediet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.popUpTo
import com.example.composediet.viewmodels.FoodHistoryViewModel
import com.example.composediet.viewmodels.FoodItemViewModel
import com.example.composediet.viewmodels.FoodViewModel
import com.example.composediet.viewmodels.ProfileViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@ExperimentalComposeUiApi
@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    foodViewModel: FoodViewModel,
    foodHistoryViewModel: FoodHistoryViewModel,
    profileViewModel: ProfileViewModel,
    prop:String?
) {
    val toastText = rememberSaveable { mutableStateOf("")}
    val dismiss = {
        foodViewModel.onFoodItemSelectedChange(null)
        navController.navigate(Screen.Food.route) {
            popUpTo(Screen.Profile.route) {}
        }
    }

    ProductItemDialog(
        onDelete = {
            if (foodViewModel.isIngredient(it)) {
                toastText.value = "Denied. Some dish contains this ingredient!"
            }
            else {
                foodViewModel.removeFoodItem(it)
                dismiss()
            }
        },
        onCreate = {
            if (it.name.value.toString() == "") {
                toastText.value = "Denied. Name must not be empty!"
            }
            else if (foodViewModel.foodItemExists(it.name.value.toString())) {
                toastText.value = "Denied. There is product with such name!"
            }
            else if (foodViewModel.dishExists(it.name.value.toString())) {
                toastText.value = "Denied. There is dish with such name!"
            }
            else {
                foodViewModel.addFoodItem(it)
                dismiss()
            }
        },
        onEat = {
            try {
                foodHistoryViewModel.addFoodItem(it, LocalDateTime.now())
                profileViewModel.onKilocaloriesAchievedChange((it.kilocalories.value!!.toFloat() * it.num.value!!.toFloat() / 100).toInt().toShort())
                profileViewModel.onDrunkWaterNumChange(it.water.value!!.toShort())
                profileViewModel.onProteinsAchievedChange((it.proteins.value!!.toFloat() * it.num.value!!.toFloat() / 100).toInt().toShort())
                profileViewModel.onFatsAchievedChange((it.fats.value!!.toFloat() * it.num.value!!.toFloat() / 100).toInt().toShort())
                profileViewModel.onCarbohydratesAchievedChange((it.carbohydrates.value!!.toFloat() * it.num.value!!.toFloat() / 100).toInt().toShort())
                dismiss()
            }
            catch (e: NumberFormatException) {
                toastText.value = "Denied. It's impossible!"
            }
        },
        prop = prop,
        foodItem = if (foodViewModel.foodItemSelected.value != null) foodViewModel.foodItemSelected.value!! else FoodItemViewModel()
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
private fun ProductItemDialog(
    onDelete: (FoodItemViewModel) -> Unit = {},
    onEat: (FoodItemViewModel) -> Unit = {},
    onCreate: (FoodItemViewModel) -> Unit = {},
    prop: String?,
    foodItem: FoodItemViewModel
) {
    val (name, setName) = rememberSaveable { mutableStateOf(foodItem.name.value.toString()) }
    val (proteins, setProteins) = rememberSaveable { mutableStateOf(foodItem.proteins.value!!.toInt()) }
    val (fats, setFats) = rememberSaveable { mutableStateOf(foodItem.fats.value!!.toInt()) }
    val (carbohydrates, setCarbohydrates) = rememberSaveable { mutableStateOf(foodItem.carbohydrates.value!!.toInt()) }
    val (water, setWater) = rememberSaveable { mutableStateOf(foodItem.water.value!!.toInt()) }
    val (kilocalories, setKilocalories) = rememberSaveable { mutableStateOf(foodItem.kilocalories.value!!.toInt()) }
    val (num, setNum) = rememberSaveable { mutableStateOf(foodItem.num.value!!.toInt()) }

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
                    foodItem.onNameChange(it)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Proteins",
                value = proteins,
                onValueChange = {
                    setProteins(it)
                    foodItem.onProteinsChange(it)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Fats",
                value = fats,
                onValueChange = {
                    setFats(it)
                    foodItem.onFatsChange(it)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Carbohydrates",
                value = carbohydrates,
                onValueChange = {
                    setCarbohydrates(it)
                    foodItem.onCarbohydratesChange(it)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Water",
                value = water,
                onValueChange = {
                    setWater(it)
                    foodItem.onWaterChange(it)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Kilocalories",
                value = kilocalories,
                onValueChange = {
                    setKilocalories(it)
                    foodItem.onKilocaloriesChange(it)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "amount",
                value = num,
                onValueChange = {
                    setNum(it)
                    foodItem.onNumChange(it)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        when(prop) {
            "create" -> {
                item {
                    Button(
                        onClick = {
                            foodItem.onNameChange(name)
                            foodItem.onProteinsChange(proteins)
                            foodItem.onFatsChange(fats)
                            foodItem.onCarbohydratesChange(carbohydrates)
                            foodItem.onWaterChange(water)
                            foodItem.onKilocaloriesChange(kilocalories)
                            onCreate(foodItem)
                       },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(text = "Create")
                    }
                }
            }
            "review" -> {
                item {
                    Button(
                        onClick = { onDelete(foodItem) },
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
                        onClick = { onEat(foodItem) },
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