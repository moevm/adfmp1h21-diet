package com.example.composediet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun ProductsSelectionScreen (
    navController: NavHostController,
    foodViewModel: FoodViewModel
) {
    val foodItems: Set<FoodItemViewModel> by foodViewModel.foodItems.observeAsState(setOf())
    val targetDish: DishViewModel by foodViewModel.targetDish.observeAsState(DishViewModel())

    val dismiss = {
        navController.popBackStack()
    }

//    FoodSearchInput(onItemComplete = { })
    Box {
        LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
            items(items = foodItems.toList()) { foodItem ->
                FoodItemSelectionRow(
                    foodItemViewModel = foodItem,
                    onCheckChange = {
                        if (it) {
                            targetDish.addIngredient(foodItem)
                        }
                        else {
                            targetDish.removeIngredient(foodItem)
                        }
                    },
                    checked =  targetDish.exists(foodItem.name.value.toString())
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .height(56.dp),
        ) {
            Button(
                onClick = {
                    dismiss()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(text = "Select")
            }
        }
    }
}

@Composable
fun FoodItemSelectionRow(
    foodItemViewModel: FoodItemViewModel,
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit
){
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.defaultMinSize(minWidth = 150.dp)) {
            Text(
                text = foodItemViewModel.name.value.toString(),
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
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
        Checkbox(checked = checked, onCheckedChange = onCheckChange)
    }
}