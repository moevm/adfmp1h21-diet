package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalDate.now
import java.util.*

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

    private val _height = MutableLiveData(-1)
    val height: LiveData<Int> = _height

    private val _weight = MutableLiveData(-1)
    val weight: LiveData<Int> = _weight

    private val _age = MutableLiveData(-1)
    val age: LiveData<Int> = _age

    private val _sex = MutableLiveData(Sex.Undefined)
    val sex: LiveData<Sex> = _sex

    private val _diet = MutableLiveData(Diet.Undefined)
    val diet: LiveData<Diet> = _diet

    private val _dateBegin = MutableLiveData(LocalDate.now())
    val dateBegin: LiveData<LocalDate> = _dateBegin

    private val _dateEnd = MutableLiveData(LocalDate.now())
    val dateEnd: LiveData<LocalDate> = _dateEnd

    fun onHeightChange(newHeight: Int) {
        _height.value = newHeight
    }

    fun onWeightChange(newWeight: Int) {
        _weight.value = newWeight
    }

    fun onAgeChange(newAge: Int) {
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