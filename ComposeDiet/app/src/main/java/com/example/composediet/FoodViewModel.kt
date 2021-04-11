package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.composediet.data.FoodItem
import com.example.composediet.repositories.FoodItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class FoodViewModel
//    @Inject constructor(private val foodItemRepository: FoodItemRepository)
    :
    ViewModel() {
//    private var _foodItems = MutableLiveData(setOf<FoodItemViewModel>())

    private var _foodItemSelected = MutableLiveData<FoodItemViewModel?>(null)
    val foodItemSelected: LiveData<FoodItemViewModel?> = _foodItemSelected

    private var _dishes = MutableLiveData(setOf<DishViewModel>())
    val dishes: LiveData<Set<DishViewModel>> = _dishes

    private var _targetDish = MutableLiveData<DishViewModel>(null)
    val targetDish: LiveData<DishViewModel> = _targetDish

    fun addFoodItem(newFoodItem: FoodItemViewModel) {
//        _foodItems.value = _foodItems.value!! + setOf(newFoodItem)
    }

    fun removeFoodItem(item: FoodItemViewModel) {
//        _foodItems.value = _foodItems.value!!.toMutableSet().also {
//            it.remove(item)
//        }
    }

    fun onFoodItemSelectedChange(item: FoodItemViewModel?) {
        _foodItemSelected.value = item
    }

    fun addDish(newDish: DishViewModel) {
        _dishes.value = _dishes.value!! + setOf(newDish)
    }

    fun removeDish(dish: DishViewModel) {
        _dishes.value = _dishes.value!!.toMutableSet().also {
            it.remove(dish)
        }
    }

    fun onTargetDishChange(dish: DishViewModel?) {
        _targetDish.value = dish
    }

    fun foodItemExists(targetName: String): Boolean {
//        return !foodItems.value!!.none { it.name.value == targetName }
        return false
    }

    fun dishExists(targetName: String): Boolean {
//        return !dishes.value!!.none { it.name.value == targetName }
        return false
    }

    fun isIngredient(foodItem: FoodItemViewModel): Boolean {
//        for (dish in _dishes.value!!) {
//            if (dish.exists(foodItem.name.value.toString())) {
//                return true
//            }
//        }
        return false
    }
}