package com.example.composediet.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalDateTime

class FoodHistoryViewModel : ViewModel() {
    private var _foodItemsAndTime = MutableLiveData(listOf<Pair<LocalDateTime, FoodItemViewModel>>())
    val foodItemsAndTime: LiveData<List<Pair<LocalDateTime, FoodItemViewModel>>> = _foodItemsAndTime

    private var _dishAndTime = MutableLiveData(listOf<Pair<LocalDateTime, DishViewModel>>())
    val dishAndTime: LiveData<List<Pair<LocalDateTime, DishViewModel>>> = _dishAndTime

    fun addFoodItem(newFoodItem: FoodItemViewModel, dateTime: LocalDateTime) {
        _foodItemsAndTime.value = _foodItemsAndTime.value!! + setOf(Pair(dateTime, newFoodItem))
    }

    fun addDish(newDish: DishViewModel, dateTime: LocalDateTime) {
        _dishAndTime.value = _dishAndTime.value!! + setOf(Pair(dateTime, newDish))
    }

    fun getDishesByDay(localDate: LocalDate): List<Pair<LocalDateTime, DishViewModel>> {
        return _dishAndTime.value!!.filter {
            it.first.year == localDate.year &&
            it.first.month == localDate.month &&
            it.first.dayOfMonth == localDate.dayOfMonth
        }
    }

    fun getFoodItemsByDay(localDate: LocalDate): List<Pair<LocalDateTime, FoodItemViewModel>> {
        return _foodItemsAndTime.value!!.filter {
            it.first.year == localDate.year &&
            it.first.month == localDate.month &&
            it.first.dayOfMonth == localDate.dayOfMonth
        }
    }
}