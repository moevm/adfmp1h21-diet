package com.example.composediet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class FoodItem(
    @PrimaryKey(autoGenerate = true)
    var foodItemId: Long,
    var name: String,
    var proteins: Short,
    var fats: Short,
    var carbohydrates: Short,
    var water: Short,
    var calories: Short,
    var num: Short
)