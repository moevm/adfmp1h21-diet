package com.example.composediet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.navigate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.*


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

@Composable
fun FoodScreen(
    items: List<FoodItem>,
    onAddItem: (FoodItem) -> Unit,
    onRemoveItem: (FoodItem) -> Unit
) {
    var b: Boolean by remember { mutableStateOf(false)}
    Column() {
        FoodSearchInput(onItemComplete = { })
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                contentPadding = PaddingValues(top = 8.dp)
            ) {
                items(items = items) {
                    FoodRow(
                        foodItem = it,
                        onItemClicked = {},
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
            FloatingActionButton(
                onClick = { b = true},
                backgroundColor = Color.Red,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 68.dp)
                    .padding(end = 12.dp)
            )
            {
                Text("+", fontSize = 30.sp, color = Color.White)
            }
            if (b) {
            }
        }
    }
}

@Composable
private fun emptyView() {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "message",
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}