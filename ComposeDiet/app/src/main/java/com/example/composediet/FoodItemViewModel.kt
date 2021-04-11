package com.example.composediet

import androidx.lifecycle.*
import com.example.composediet.calendar.data.DatesRepository
import com.example.composediet.data.FoodItem
import com.example.composediet.repositories.FoodItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
open class FoodItemViewModel
    @Inject internal constructor(private val foodItemRepository: FoodItemRepository) :ViewModel() {
    val foodItems: LiveData<List<FoodItem>> = foodItemRepository.foodItems.asLiveData()
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

    fun onNameChange(id: Long, name: String) = viewModelScope.launch {
        foodItemRepository.updateName(id, name)
    }

//    fun getProteins(): MutableLiveData<Int> {
//        return _proteins
//    }

    fun onProteinsChange(id: Long, proteins: Short) = viewModelScope.launch {
        foodItemRepository.updateProteins(id, proteins)
    }

//    fun getFats(): MutableLiveData<Int> {
//        return _fats
//    }
    fun onFatsChange(id: Long, fats: Short) = viewModelScope.launch {
        foodItemRepository.updateFats(id, fats)
    }

//    fun getCarbohydrates(): MutableLiveData<Int> {
//        return _carbohydrates
//    }

    fun onCarbohydratesChange(id: Long, carbohydrates: Short) = viewModelScope.launch {
        foodItemRepository.updateCarbohydrates(id, carbohydrates)
    }

//    fun getWater(): MutableLiveData<Int> {
//        return _water
//    }

    fun onWaterChange(id: Long, water: Short) = viewModelScope.launch {
        foodItemRepository.updateWater(id, water)
    }

//    fun getKilocalories(): MutableLiveData<Int> {
//        return _kilocalories
//    }

    fun onKilocaloriesChange(id: Long, calories: Short) = viewModelScope.launch {
        foodItemRepository.updateCalories(id, calories)
    }

    fun onNumChange(id: Long, num: Short) = viewModelScope.launch {
        foodItemRepository.updateNum(id, num)
    }
}