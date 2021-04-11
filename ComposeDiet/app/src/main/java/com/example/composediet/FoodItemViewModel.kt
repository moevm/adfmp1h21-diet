package com.example.composediet

import androidx.lifecycle.*
import com.example.composediet.data.FoodItem
import com.example.composediet.repositories.FoodItemRepository
import kotlinx.coroutines.launch
import java.util.*

open class FoodItemViewModel(private val foodItemRepository: FoodItemRepository): ViewModel() {
    val foodItems: LiveData<Set<FoodItem>> = foodItemRepository.foodItems.asLiveData()
//    private val _name = MutableLiveData("")
//    val name: LiveData<String> = _name
//
//    private val _proteins = MutableLiveData(0)
//    val proteins: LiveData<Int> = _proteins
//
//    private val _fats = MutableLiveData(0)
//    val fats: LiveData<Int> = _fats
//
//    private val _carbohydrates = MutableLiveData(0)
//    val carbohydrates: LiveData<Int> = _carbohydrates
//
//    private val _water = MutableLiveData(0)
//    val water: LiveData<Int> = _water
//
//    private val _kilocalories = MutableLiveData(0)
//    val kilocalories: LiveData<Int> = _kilocalories
//
//    private val _num = MutableLiveData(0)
//    val num: LiveData<Int> = _num

//    val id: UUID = UUID.randomUUID()

    fun onNameChange(id: ULong, name: String) = viewModelScope.launch {
        foodItemRepository.updateName(id, name)
    }

//    fun getProteins(): MutableLiveData<Int> {
//        return _proteins
//    }

    fun onProteinsChange(id: ULong, proteins: Short) = viewModelScope.launch {
        foodItemRepository.updateProteins(id, proteins)
    }

//    fun getFats(): MutableLiveData<Int> {
//        return _fats
//    }
    fun onFatsChange(id: ULong, fats: Short) = viewModelScope.launch {
        foodItemRepository.updateFats(id, fats)
    }

//    fun getCarbohydrates(): MutableLiveData<Int> {
//        return _carbohydrates
//    }

    fun onCarbohydratesChange(id: ULong, carbohydrates: Short) = viewModelScope.launch {
        foodItemRepository.updateCarbohydrates(id, carbohydrates)
    }

//    fun getWater(): MutableLiveData<Int> {
//        return _water
//    }

    fun onWaterChange(id: ULong, water: Short) = viewModelScope.launch {
        foodItemRepository.updateWater(id, water)
    }

//    fun getKilocalories(): MutableLiveData<Int> {
//        return _kilocalories
//    }

    fun onKilocaloriesChange(id: ULong, calories: Short) = viewModelScope.launch {
        foodItemRepository.updateCalories(id, calories)
    }

    fun onNumChange(id: ULong, num: Short) = viewModelScope.launch {
        foodItemRepository.updateNum(id, num)
    }
}