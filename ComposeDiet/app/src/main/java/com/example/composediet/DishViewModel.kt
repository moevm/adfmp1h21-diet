package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DishViewModel: FoodItemViewModel() {
    private var _ingredients = MutableLiveData(setOf<FoodItemViewModel>())
    val ingredients: LiveData<Set<FoodItemViewModel>> = _ingredients

    fun addIngredient(newIngredient: FoodItemViewModel) {
        _ingredients.value = _ingredients.value!! + setOf(newIngredient)
    }
    fun addIngredients(ingredients: Set<FoodItemViewModel>) {
        _ingredients.value = _ingredients.value!! + ingredients
    }

    fun exists(targetIngredientName: String): Boolean {
        return !ingredients.value!!.none { it.name.value == targetIngredientName }
    }
    fun removeIngredient(ingredient: FoodItemViewModel) {
        _ingredients.value = _ingredients.value!!.toMutableSet().also {
            it.remove(ingredient)
        }
    }
}