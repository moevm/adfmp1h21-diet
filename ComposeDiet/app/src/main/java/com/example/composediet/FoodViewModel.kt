package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

data class FoodItem(
    val name: String,
    val id: UUID = UUID.randomUUID()
)


class FoodViewModel : ViewModel() {

    private var _foodItems = MutableLiveData(listOf<FoodItem>())
    val foodItems: LiveData<List<FoodItem>> = _foodItems

    fun addItem(item: FoodItem) {
        _foodItems.value = _foodItems.value!! + listOf(item)
    }

    fun removeItem(item: FoodItem) {
        _foodItems.value = _foodItems.value!!.toMutableList().also {
            it.remove(item)
        }
    }
}