package com.example.composediet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class FoodItem(
    @PrimaryKey(autoGenerate = true)
    val id: ULong,
    val name: String,
    val proteins: Short,
    val fats: Short,
    val carbohydrates: Short,
    val water: Short,
    val calories: Short,
    val num: Short
)