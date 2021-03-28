package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DishViewModel: FoodItemViewModel() {
    private var _ingredients = MutableLiveData(listOf<FoodItemViewModel>())
    val ingredients: LiveData<List<FoodItemViewModel>> = _ingredients

    fun addIngredient(newIngredient: FoodItemViewModel) {
        _ingredients.value = _ingredients.value!! + listOf(newIngredient)
    }

    fun exists(targetIngredientName: String): Boolean {
        return !ingredients.value!!.none { it.name.value == targetIngredientName }
    }
    fun removeIngredient(ingredient: FoodItemViewModel) {
        _ingredients.value = _ingredients.value!!.toMutableList().also {
            it.remove(ingredient)
        }
    }
}