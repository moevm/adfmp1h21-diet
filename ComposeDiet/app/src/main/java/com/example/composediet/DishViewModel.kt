package com.example.composediet

import androidx.lifecycle.*
import com.example.composediet.data.DishWithProducts
import com.example.composediet.data.FoodItem
import com.example.composediet.repositories.DishRepository
import com.example.composediet.repositories.FoodItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishViewModel
@Inject internal constructor(private val dishRepository: DishRepository): ViewModel() {
    val ingredients: LiveData<List<DishWithProducts>> = dishRepository.dishes.asLiveData()

    fun addIngredient(ingredient: FoodItem) = viewModelScope.launch {
        val foodItems = listOf(ingredient)
        dishRepository.insertFoodItems(foodItems)
    }

    fun exists(targetIngredientName: String): Boolean {
        return false
    }
    fun removeIngredient(ingredient: FoodItem) = viewModelScope.launch {
        val foodItems = listOf(ingredient)
        dishRepository.deleteFoodItems(foodItems)
    }
}