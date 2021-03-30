package com.example.composediet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.math.roundToInt

fun Float.roundToDecimals(decimals: Int): Float {
    var dotAt = 1
    repeat(decimals) { dotAt *= 10 }
    val roundedValue = (this * dotAt).roundToInt()
    return (roundedValue / dotAt) + (roundedValue % dotAt).toFloat() / dotAt
}

@ExperimentalFoundationApi
@Composable
fun WaterScreen(profileViewModel: ProfileViewModel, navController: NavHostController) {
    val drunkWaterNum: Short by profileViewModel.drunkWaterNum.observeAsState(0.toShort())
    val oneGlass250: Float = 0.25F

    Navigation(navController = navController) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    Text(
                        text = "Aim: ",
                        modifier = Modifier.padding(8.dp),
                        style = typography.h6.copy(fontSize = 24.sp)
                    )
                    Text(
                        text = profileViewModel.waterNumToDrinkAim.value?.times(oneGlass250)
                            ?.roundToDecimals(3)
                            .toString(),
                        modifier = Modifier.padding(8.dp),
                        style = typography.h6.copy(fontSize = 24.sp)
                    )
                    Text(
                        text = " litres",
                        modifier = Modifier.padding(8.dp),
                        style = typography.h6.copy(fontSize = 24.sp)
                    )
                }
                Row() {
                    Text(
                        text = "Achieved: ",
                        modifier = Modifier.padding(8.dp),
                        style = typography.h6.copy(fontSize = 24.sp)
                    )
                    Text(
                        text = drunkWaterNum?.times(oneGlass250)
                            ?.roundToDecimals(3)
                            .toString(),
                        modifier = Modifier.padding(8.dp),
                        style = typography.h6.copy(fontSize = 24.sp)
                    )
                    Text(
                        text = " litres",
                        modifier = Modifier.padding(8.dp),
                        style = typography.h6.copy(fontSize = 24.sp)
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
                modifier = Modifier.padding(bottom = 64.dp)
            ) {
                for (i in 0 until drunkWaterNum) {
                    item {
                        Image(
                            painter = painterResource(R.drawable.full_glass_of_water),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { profileViewModel.onDrunkWaterNumChange(i.toShort()) }
                        )
                    }
                }
                if (drunkWaterNum < profileViewModel.waterNumToDrinkAim.value!!) {
                    for (i in 0 until profileViewModel.waterNumToDrinkAim.value!! - drunkWaterNum) {
                        item {
                            Image(
                                painter = painterResource(R.drawable.empty_glass_of_water),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        profileViewModel.onDrunkWaterNumChange((drunkWaterNum + i + 1).toShort())
                                    }
                            )
                        }
                    }
                }
                else {
                    item {
                        Image(
                            painter = painterResource(R.drawable.empty_glass_of_water),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { profileViewModel.onDrunkWaterNumChange((drunkWaterNum + 1).toShort()) }
                        )
                    }
                }
            }
        }
    }
}