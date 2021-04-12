package com.example.composediet.model

import androidx.lifecycle.*
import com.example.composediet.repositories.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DishWithProductsViewModel
@Inject internal constructor(private val dishRepository: DishRepository): ViewModel() {
//    fun getDishes() =  dishRepository.getDishWithProducts()
//
//    fun addIngredient(ingredient: FoodItem) = viewModelScope.launch {
//        val foodItems = listOf(ingredient)
//        dishRepository.insertFoodItems(foodItems)
//    }
//
//    fun exists(targetIngredientName: String): Boolean {
//        return false
//    }
//    fun removeIngredient(ingredient: FoodItem) = viewModelScope.launch {
//        val foodItems = listOf(ingredient)
//        dishRepository.deleteFoodItems(foodItems)
//    }
}