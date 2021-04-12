package com.example.composediet.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

open class FoodItemViewModel(): ViewModel() {
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

    private val _num = MutableLiveData(0)
    val num: LiveData<Int> = _num

    val id: UUID = UUID.randomUUID()

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun getProteins(): MutableLiveData<Int> {
        return _proteins
    }

    fun onProteinsChange(newProteins: Int) {
        _proteins.value = newProteins
    }

    fun getFats(): MutableLiveData<Int> {
        return _fats
    }
    fun onFatsChange(newFats: Int) {
        _fats.value = newFats
    }

    fun getCarbohydrates(): MutableLiveData<Int> {
        return _carbohydrates
    }

    fun onCarbohydratesChange(newCarbohydrates: Int) {
        _carbohydrates.value = newCarbohydrates
    }

    fun getWater(): MutableLiveData<Int> {
        return _water
    }

    fun onWaterChange(newWater: Int) {
        _water.value = newWater
    }

    fun getKilocalories(): MutableLiveData<Int> {
        return _kilocalories
    }

    fun onKilocaloriesChange(newKilocalories: Int) {
        _kilocalories.value = newKilocalories
    }

    fun onNumChange(newNum: Int) {
        _num.value = newNum
    }
}