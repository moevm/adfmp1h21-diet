package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class FoodItem(): ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _proteins = MutableLiveData(0)
    val proteins: LiveData<Int> = _proteins

    private val _fats = MutableLiveData(0)
    val fats: LiveData<Int> = _fats

    private val _carbohydrates = MutableLiveData(0)
    val carbohydrates: LiveData<Int> = _carbohydrates

    private val _water = MutableLiveData(0)
    val water: LiveData<Int> = _water

    private val _kilocalories = MutableLiveData(0)
    val kilocalories: LiveData<Int> = _kilocalories

    val id: UUID = UUID.randomUUID()

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onProteinsChange(newProteins: Int) {
        _proteins.value = newProteins
    }

    fun onFatsChange(newFats: Int) {
        _fats.value = newFats
    }

    fun onCarbohydratesChange(newCarbohydrates: Int) {
        _carbohydrates.value = newCarbohydrates
    }

    fun onWaterChange(newWater: Int) {
        _water.value = newWater
    }

    fun onKilocaloriesChange(newKilocalories: Int) {
        _kilocalories.value = newKilocalories
    }
}


class FoodViewModel : ViewModel() {

    private var _foodItems = MutableLiveData(listOf<FoodItem>())
    val foodItems: LiveData<List<FoodItem>> = _foodItems

    private var _foodItemSelected = MutableLiveData<FoodItem?>(null)
    val foodItemSelected: LiveData<FoodItem?> = _foodItemSelected

    fun addItem(newItem: FoodItem) {
        _foodItems.value = _foodItems.value!! + listOf(newItem)
    }

    fun exists(targetName: String): Boolean {
        return !foodItems.value!!.none { it.name.value == targetName }
    }
    fun removeItem(item: FoodItem) {
        _foodItems.value = _foodItems.value!!.toMutableList().also {
            it.remove(item)
        }
    }

    fun at(i: Int): FoodItem {
        return _foodItems.value!!.get(i)
    }

    fun onFoodItemSelectedChange(item: FoodItem?) {
        _foodItemSelected.value = item
    }
}