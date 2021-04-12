package com.example.composediet

import com.example.composediet.viewmodels.Diet
import com.example.composediet.viewmodels.Sex
import java.time.LocalDate

class DietComputer(val height: Short, val weight: Short, val age: Short, val sex: Sex, val dietAim: Diet, val dateBegin: LocalDate) {
    val waterPerWeightADay: Float = 0.03f
    val proteinsPerWeightADayForMan: Float = 1.2f
    val proteinsPerWeightADayForWoman = 1
    val fatsPerWeightADay: Float = 1f
    val carbohydratesPerWeightADay: Float = 2f

    var dateEnd: LocalDate = dateBegin
    var proteins: Short = 0
    var fats: Short = 0
    var carbohydartes: Short = 0
    var kilocalories: Short = 0
    var water: Short = 0

    fun compute() {
        var A = height * 1.8
        var B = weight * 9.6
        var C = age * 4.7
        var IML = 855 + A + B - C

        var caloriesADayToHoldWeight: Short = (IML * 1.2).toInt().toShort()
        var proteinsADayToHoldWeight: Short = (if (sex == Sex.Man) weight * proteinsPerWeightADayForMan else weight * proteinsPerWeightADayForWoman).toShort()
        var fatsADayToHoldWeight = (weight * fatsPerWeightADay).toInt().toShort()
        var carbohydratesADayToHoldWeight = (weight * carbohydratesPerWeightADay).toInt().toShort()

        when(dietAim) {
            Diet.PutOnWeight -> {

            }
            Diet.HoldWeight -> {
                proteins = proteinsADayToHoldWeight
                fats = fatsADayToHoldWeight
                carbohydartes = carbohydratesADayToHoldWeight
                kilocalories = caloriesADayToHoldWeight
            }
            Diet.LoseWeight -> {

            }
        }

        water = (waterPerWeightADay * weight / 250).toInt().toShort()
    }
}