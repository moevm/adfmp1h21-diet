package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel() {

    private var _foodItems = MutableLiveData(listOf<FoodItemViewModel>())
    val foodItems: LiveData<List<FoodItemViewModel>> = _foodItems

    private var _foodItemSelected = MutableLiveData<FoodItemViewModel?>(null)
    val foodItemSelected: LiveData<FoodItemViewModel?> = _foodItemSelected

    private var _dishes = MutableLiveData(listOf<DishViewModel>())
    val dishes: LiveData<List<DishViewModel>> = _dishes

    private var _dishSelected = MutableLiveData<DishViewModel?>(null)
    val dishSelected: LiveData<DishViewModel?> = _dishSelected

    fun addFoodItem(newFoodItem: FoodItemViewModel) {
        _foodItems.value = _foodItems.value!! + listOf(newFoodItem)
    }

    fun removeFoodItem(item: FoodItemViewModel) {
        _foodItems.value = _foodItems.value!!.toMutableList().also {
            it.remove(item)
        }
    }

    fun foodItemAt(i: Int): FoodItemViewModel {
        return _foodItems.value!!.get(i)
    }

    fun onFoodItemSelectedChange(item: FoodItemViewModel?) {
        _foodItemSelected.value = item
    }

    fun addDish(newDish: DishViewModel) {
        _dishes.value = _dishes.value!! + listOf(newDish)
    }

    fun removeDish(dish: DishViewModel) {
        _dishes.value = _dishes.value!!.toMutableList().also {
            it.remove(dish)
        }
    }

    fun onDishSelectedChange(dish: DishViewModel?) {
        _dishSelected.value = dish
    }

    fun foodItemExists(targetName: String): Boolean {
        return !foodItems.value!!.none { it.name.value == targetName }
    }

    fun dishExists(targetName: String): Boolean {
        return !dishes.value!!.none { it.name.value == targetName }
    }
}