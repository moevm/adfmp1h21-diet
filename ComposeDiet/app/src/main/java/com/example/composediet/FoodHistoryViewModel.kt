package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composediet.model.DishWithProductsViewModel
import com.example.composediet.model.ProductViewModel
import java.time.LocalDate
import java.time.LocalDateTime

class FoodHistoryViewModel : ViewModel() {
    private var _foodItemsAndTime = MutableLiveData(listOf<Pair<LocalDateTime, ProductViewModel>>())
    val foodItemsAndTime: LiveData<List<Pair<LocalDateTime, ProductViewModel>>> = _foodItemsAndTime

    private var _dishAndTime = MutableLiveData(listOf<Pair<LocalDateTime, DishWithProductsViewModel>>())
    val dishWithProductsAndTime: LiveData<List<Pair<LocalDateTime, DishWithProductsViewModel>>> = _dishAndTime

    fun addFoodItem(newProduct: ProductViewModel, dateTime: LocalDateTime) {
        _foodItemsAndTime.value = _foodItemsAndTime.value!! + setOf(Pair(dateTime, newProduct))
    }

    fun addDish(newDishWithProducts: DishWithProductsViewModel, dateTime: LocalDateTime) {
        _dishAndTime.value = _dishAndTime.value!! + setOf(Pair(dateTime, newDishWithProducts))
    }

    fun getDishesByDay(localDate: LocalDate): List<Pair<LocalDateTime, DishWithProductsViewModel>> {
        return _dishAndTime.value!!.filter {
            it.first.year == localDate.year &&
            it.first.month == localDate.month &&
            it.first.dayOfMonth == localDate.dayOfMonth
        }
    }

    fun getFoodItemsByDay(localDate: LocalDate): List<Pair<LocalDateTime, ProductViewModel>> {
        return _foodItemsAndTime.value!!.filter {
            it.first.year == localDate.year &&
            it.first.month == localDate.month &&
            it.first.dayOfMonth == localDate.dayOfMonth
        }
    }
}