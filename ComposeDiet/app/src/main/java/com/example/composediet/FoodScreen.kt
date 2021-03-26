package com.example.composediet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialDialogs
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FoodSearchInput(onItemComplete: (FoodItem) -> Unit) {
    val (text, setText) = remember { mutableStateOf("") }
    val submit = {
        onItemComplete(FoodItem(text))
        setText("")
    }
    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            TextField(
                value = text,
                onValueChange = setText,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    submit()
                    keyboardController?.hideSoftwareKeyboard()
                }),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
            )
        }
    }
}

@Composable
fun FoodRow(foodItem: FoodItem, onItemClicked: (FoodItem) -> Unit, modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .clickable { onItemClicked(foodItem) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(foodItem.name)
    }
}

@Composable
fun Product(
    name: String = "",
    proteins: String = "",
    fats: String = "",
    carbohydrates: String = "",
    water: String = "",
    kilocalories: String = "")
{
    Column() {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(2.dp)) {
            Text(text = "Name")
            Text(text = name)
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier.padding(2.dp)) {
            Text(text = "proteins")
            Text(text = proteins)
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier.padding(2.dp)) {
            Text(text = "fats")
            Text(text = fats)
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(2.dp)) {
            Text(text = "carbohydrates")
            Text(text = carbohydrates)
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(2.dp)) {
            Text(text = "water")
            Text(text = water)
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(2.dp)) {
            Text(text = "kilocalories")
            Text(text = kilocalories)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FoodScreen(
    navController: NavHostController,
    items: List<FoodItem>,
    onAddItem: (FoodItem) -> Unit,
    onRemoveItem: (FoodItem) -> Unit
) {
    val toolButtonsVisibilityState = rememberSaveable { mutableStateOf(false) }
    val productItemDialogState = rememberSaveable { mutableStateOf(false) }
    val dishItemDialogState = rememberSaveable { mutableStateOf(false) }

    Navigation(navController = navController) {
        Column {
            Box(modifier = Modifier.fillMaxSize()) {
                FoodSearchInput(onItemComplete = { })
                LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
                    items(items = items) {
                        FoodRow(
                            foodItem = it,
                            onItemClicked = {},
                            modifier = Modifier.fillParentMaxWidth()
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (toolButtonsVisibilityState.value) {
                        Button(
                            onClick = { productItemDialogState.value = true},
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = "Product")
                        }
                        Button(
                            onClick = { dishItemDialogState.value = true },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = "Dish")
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                            toolButtonsVisibilityState.value = !toolButtonsVisibilityState.value
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

    ProductItemDialog(
        show = productItemDialogState.value,
        onDismissRequest = { productItemDialogState.value = false }
    )
    DishItemDialog(
        show = dishItemDialogState.value,
        onDismissRequest = { dishItemDialogState.value = false }
    )
}

@Composable
private fun ProductItemDialog(show: Boolean, onDismissRequest: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Product dialog")
                }
            }
        }
    }
}

@Composable
private fun DishItemDialog(show: Boolean, onDismissRequest: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Dish dialog")
                }
            }
        }
    }
}
