package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DishViewModel: FoodItemViewModel() {
    private var _ingredients = MutableLiveData(setOf<FoodItemViewModel>())
    val ingredients: LiveData<Set<FoodItemViewModel>> = _ingredients

    fun addIngredient(newIngredient: FoodItemViewModel) {
        getProteins().value = getProteins().value!!.plus(newIngredient.proteins.value!!)
        getFats().value = getFats().value!!.plus(newIngredient.fats.value!!)
        getCarbohydrates().value = getCarbohydrates().value!!.plus(newIngredient.carbohydrates.value!!)
        getWater().value = getWater().value!!.plus(newIngredient.water.value!!)
        getKilocalories().value = getKilocalories().value!!.plus(newIngredient.kilocalories.value!!)

        _ingredients.value = _ingredients.value!! + setOf(newIngredient)
    }

    fun exists(targetIngredientName: String): Boolean {
        return !ingredients.value!!.none { it.name.value == targetIngredientName }
    }
    fun removeIngredient(ingredient: FoodItemViewModel) {
        getProteins().value = getProteins().value!!.minus(ingredient.proteins.value!!)
        getFats().value = getFats().value!!.minus(ingredient.fats.value!!)
        getCarbohydrates().value = getCarbohydrates().value!!.minus(ingredient.carbohydrates.value!!)
        getWater().value = getWater().value!!.minus(ingredient.water.value!!)
        getKilocalories().value = getKilocalories().value!!.minus(ingredient.kilocalories.value!!)

        _ingredients.value = _ingredients.value!!.toMutableSet().also {
            it.remove(ingredient)
        }
    }
}