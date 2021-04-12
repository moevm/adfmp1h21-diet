package com.example.composediet.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composediet.data.Dish

class DishSelectedViewModel: ViewModel() {
    private val _selected = MutableLiveData<Dish?>()
    val selected: LiveData<Dish?> = _selected

    fun onSelectedChange(dish: Dish?) {
        _selected.value = dish
    }
}