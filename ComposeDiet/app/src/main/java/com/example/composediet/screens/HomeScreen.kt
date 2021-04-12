package com.example.composediet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composediet.viewmodels.ProfileViewModel

@Composable
fun HomeScreen(navController: NavHostController, profileViewModel: ProfileViewModel) {
    val drunkWaterNum: Short by profileViewModel.drunkWaterNum.observeAsState(0.toShort())
    val waterNumToDrinkAim: Short by profileViewModel.waterNumToDrinkAim.observeAsState(0.toShort())

    val kilocaloriesAchieved: Short by profileViewModel.kilocaloriesAchieved.observeAsState(0.toShort())
    val kilocalories: Short by profileViewModel.kilocalories.observeAsState(0.toShort())

    val proteinsAchieved: Short by profileViewModel.proteinsAchieved.observeAsState(0.toShort())
    val proteins: Short by profileViewModel.proteins.observeAsState(0.toShort())

    val carbohydratesAchieved: Short by profileViewModel.carbohydratesAchieved.observeAsState(0.toShort())
    val carbohydrates: Short by profileViewModel.carbohydrates.observeAsState(0.toShort())

    val fatsAchieved: Short by profileViewModel.fatsAchieved.observeAsState(0.toShort())
    val fats: Short by profileViewModel.fats.observeAsState(0.toShort())

    Navigation(navController = navController) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(8.dp)) {
                    Text(text = "calories:", modifier = Modifier.padding(8.dp))
                    Text(text = "water:", modifier = Modifier.padding(8.dp))
                    Text(text = "proteins:", modifier = Modifier.padding(8.dp))
                    Text(text = "fats:", modifier = Modifier.padding(8.dp))
                    Text(text = "carbohydrates:", modifier = Modifier.padding(8.dp))
                }
                Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(8.dp)) {
                    Text(text = "$kilocaloriesAchieved/$kilocalories", modifier = Modifier.padding(8.dp))
                    Text(text = "${drunkWaterNum * 0.25f}/$waterNumToDrinkAim", modifier = Modifier.padding(8.dp))
                    Text(text = "$proteinsAchieved/$proteins", modifier = Modifier.padding(8.dp))
                    Text(text = "$fatsAchieved/$fats", modifier = Modifier.padding(8.dp))
                    Text(text = "$carbohydratesAchieved/$carbohydrates", modifier = Modifier.padding(8.dp))
                }

                Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                    Text(text = "kcal", modifier = Modifier.padding(8.dp))
                    Text(text = "l", modifier = Modifier.padding(8.dp))
                    Text(text = "g", modifier = Modifier.padding(8.dp))
                    Text(text = "g", modifier = Modifier.padding(8.dp))
                    Text(text = "g", modifier = Modifier.padding(8.dp))
                }

        }
    }
}