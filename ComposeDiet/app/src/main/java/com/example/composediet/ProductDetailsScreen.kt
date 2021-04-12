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
import com.example.composediet.data.Product
import com.example.composediet.model.ProductSelectedViewModel
import com.example.composediet.model.ProductViewModel
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel,
    foodHistoryViewModel: FoodHistoryViewModel,
    profileViewModel: ProfileViewModel,
    prop:String?
) {
    val toastText = rememberSaveable { mutableStateOf("")}
    val dismiss = {
        navController.navigate(Screen.Food.route) {
            popUpTo(Screen.Profile.route) {}
        }
    }
    ProductItemDialog(
        onDelete = {
            if (productViewModel.isIngredient(it)) {
                toastText.value = "Denied. Some dish contains this ingredient!"
            }
            else {
                productViewModel.onDeleteProduct()
                dismiss()
            }
        },
        onCreate = {
            if (it.name == "") {
                toastText.value = "Denied. Name must not be empty!"
            }
//            else if (foodViewModel.foodItemExists(it.name)) {
//                toastText.value = "Denied. There is product with such name!"
//            }
//            else if (foodViewModel.dishExists(it.name)) {
//                toastText.value = "Denied. There is dish with such name!"
//            }
            else {
                productViewModel.onAddProduct()
                dismiss()
            }
        },
        onEat = {
            try {
//                foodHistoryViewModel.addFoodItem(it, LocalDateTime.now())
//                profileViewModel.onKilocaloriesAchievedChange((it.kilocalories.value!!.toFloat() * it.num.value!!.toFloat() / 100).toInt().toShort())
//                profileViewModel.onDrunkWaterNumChange(it.water.value!!.toShort())
//                profileViewModel.onProteinsAchievedChange((it.proteins.value!!.toFloat() * it.num.value!!.toFloat() / 100).toInt().toShort())
//                profileViewModel.onFatsAchievedChange((it.fats.value!!.toFloat() * it.num.value!!.toFloat() / 100).toInt().toShort())
//                profileViewModel.onCarbohydratesAchievedChange((it.carbohydrates.value!!.toFloat() * it.num.value!!.toFloat() / 100).toInt().toShort())
                dismiss()
            }
            catch (e: NumberFormatException) {
                toastText.value = "Denied. It's impossible!"
            }
        },
        prop = prop,
        product = if (foodViewModel.foodItemSelected.value != null) foodViewModel.foodItemSelected.value!! else Product(-1, "", 0, 0, 0, 0, 0, 0)
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
    onDelete: (Product) -> Unit = {},
    onEat: (Product) -> Unit = {},
    onCreate: (Product) -> Unit = {},
    prop: String?,
    product: Product
) {
    val (name, setName) = rememberSaveable { mutableStateOf(product.name) }
    val (proteins, setProteins) = rememberSaveable { mutableStateOf(product.proteins) }
    val (fats, setFats) = rememberSaveable { mutableStateOf(product.fats) }
    val (carbohydrates, setCarbohydrates) = rememberSaveable { mutableStateOf(product.carbohydrates) }
    val (water, setWater) = rememberSaveable { mutableStateOf(product.water) }
    val (calories, setKilocalories) = rememberSaveable { mutableStateOf(product.calories) }
    val (num, setNum) = rememberSaveable { mutableStateOf(product.num) }

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
                    product.name = it
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Proteins",
                value = proteins.toInt(),
                onValueChange = {
                    setProteins(it.toShort())
                    product.proteins = it.toShort()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Fats",
                value = fats.toInt(),
                onValueChange = {
                    setFats(it.toShort())
                    product.fats = it.toShort()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Carbohydrates",
                value = carbohydrates.toInt(),
                onValueChange = {
                    setCarbohydrates(it.toShort())
                    product.carbohydrates = it.toShort()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Water",
                value = water.toInt(),
                onValueChange = {
                    setWater(it.toShort())
                    product.water = it.toShort()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "Kilocalories",
                value = calories.toInt(),
                onValueChange = {
                    setKilocalories(it.toShort())
                    product.calories = it.toShort()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        item {
            UnsignedIntInput(
                name = "amount",
                value = num.toInt(),
                onValueChange = {
                    setNum(it.toShort())
                    product.num = it.toShort()
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

                            product.name = name
                            product.proteins = proteins
                            product.fats = fats
                            product.carbohydrates = carbohydrates
                            product.water = water
                            product.calories = calories
                            onCreate(product)
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
                        onClick = { onDelete(product) },
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
                        onClick = { onEat(product) },
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