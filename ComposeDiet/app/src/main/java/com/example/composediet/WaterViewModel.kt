package com.example.composediet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WaterViewModel : ViewModel() {
    private val _drunkWaterNum = MutableLiveData(0)
    val drunkWaterNum: LiveData<Int> = _drunkWaterNum

    private val _waterNumToDrinkAim = MutableLiveData(8)
    val waterNumToDrinkAim: LiveData<Int> = _waterNumToDrinkAim

    fun onDrunkWaterNumChange(newNum: Int) {
        _drunkWaterNum.value = newNum
    }
}