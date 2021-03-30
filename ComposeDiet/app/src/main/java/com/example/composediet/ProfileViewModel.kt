package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

enum class Sex {
    Man,
    Woman,
    Undefined
}

enum class Diet {
    LoseWeight,
    GetFat,
    HoldWeight,
    Undefined
}

class ProfileViewModel : ViewModel() {

    private val _height = MutableLiveData((-1).toShort())
    val height: LiveData<Short> = _height

    private val _weight = MutableLiveData((-1).toShort())
    val weight: LiveData<Short> = _weight

    private val _age = MutableLiveData((-1).toShort())
    val age: LiveData<Short> = _age

    private val _sex = MutableLiveData(Sex.Undefined)
    val sex: LiveData<Sex> = _sex

    private val _diet = MutableLiveData(Diet.Undefined)
    val diet: LiveData<Diet> = _diet

    private val _dateBegin = MutableLiveData(LocalDate.now())
    val dateBegin: LiveData<LocalDate> = _dateBegin

    private val _dateEnd = MutableLiveData(LocalDate.now())
    val dateEnd: LiveData<LocalDate> = _dateEnd

    private val _drunkWaterNum = MutableLiveData(0.toShort())
    val drunkWaterNum: LiveData<Short> = _drunkWaterNum

    private val _waterNumToDrinkAim = MutableLiveData(0.toShort())
    val waterNumToDrinkAim: LiveData<Short> = _waterNumToDrinkAim

    private val _proteins = MutableLiveData(0.toShort())
    val proteins: LiveData<Short> = _proteins

    private val _proteinsAchieved = MutableLiveData(0.toShort())
    val proteinsAchieved: LiveData<Short> = _proteinsAchieved

    private val _fats = MutableLiveData(0.toShort())
    val fats: LiveData<Short> = _fats

    private val _fatsAchieved = MutableLiveData(0.toShort())
    val fatsAchieved: LiveData<Short> = _fatsAchieved

    private val _carbohydrates = MutableLiveData(0.toShort())
    val carbohydrates: LiveData<Short> = _carbohydrates

    private val _carbohydratesAchieved = MutableLiveData(0.toShort())
    val carbohydratesAchieved: LiveData<Short> = _carbohydratesAchieved

    private val _kilocalories = MutableLiveData(0.toShort())
    val kilocalories: LiveData<Short> = _kilocalories

    private val _kilocaloriesAchieved = MutableLiveData(0.toShort())
    val kilocaloriesAchieved: LiveData<Short> = _kilocaloriesAchieved

    fun getProteins(): MutableLiveData<Short> {
        return _proteins
    }

    fun onProteinsChange(newProteins: Short) {
        _proteins.value = newProteins
    }

    fun onProteinsAchievedChange(num: Short) {
        _proteinsAchieved.value = (_proteins.value!! + num).toShort()
    }

    fun getFats(): MutableLiveData<Short> {
        return _fats
    }

    fun onFatsChange(newFats: Short) {
        _fats.value = newFats
    }

    fun onFatsAchievedChange(num: Short) {
        _fatsAchieved.value = (_fatsAchieved.value!! + num).toShort()
    }

    fun getCarbohydrates(): MutableLiveData<Short> {
        return _carbohydrates
    }

    fun onCarbohydratesChange(newCarbohydrates: Short) {
        _carbohydrates.value = newCarbohydrates
    }

    fun onCarbohydratesAchievedChange(num: Short) {
        _carbohydratesAchieved.value = (_carbohydrates.value!! + num).toShort()
    }

    fun getKilocalories(): MutableLiveData<Short> {
        return _kilocalories
    }

    fun onKilocaloriesChange(newKilocalories: Short) {
        _kilocalories.value = newKilocalories
    }

    fun onKilocaloriesAchievedChange(num: Short) {
        _kilocaloriesAchieved.value = (_kilocaloriesAchieved.value!! + num).toShort()
    }

    fun onDrunkWaterNumChange(newNum: Short) {
        _drunkWaterNum.value = newNum
    }

    fun onHeightChange(newHeight: Short) {
        _height.value = newHeight
    }

    fun onWeightChange(newWeight: Short) {
        _weight.value = newWeight
    }

    fun onAgeChange(newAge: Short) {
        _age.value = newAge
    }

    fun onSexChange(newSex: Sex) {
        _sex.value = newSex
    }

    fun onDietChange(newDiet: Diet) {
        _diet.value = newDiet
    }

    fun onDateBeginChange(newDateBegin: LocalDate) {
        _dateBegin.value = newDateBegin
    }

    fun onDateEndChange(newDateEnd: LocalDate) {
        _dateEnd.value = newDateEnd
    }
}