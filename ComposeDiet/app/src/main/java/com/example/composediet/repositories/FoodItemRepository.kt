package com.example.composediet.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.composediet.dao.FoodItemDao
import com.example.composediet.data.FoodItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodItemRepository
    @Inject constructor(private val foodItemDao: FoodItemDao) {
    val foodItems: Flow<List<FoodItem>> = foodItemDao.getAllFoodItems()

    suspend fun insert(foodItem: FoodItem) {
        foodItemDao.insert(foodItem)
    }

    suspend fun delete(name: String) {
        foodItemDao.delete(name)
    }

    suspend fun update(foodItem: FoodItem) {
        foodItemDao.update(foodItem)
    }

    suspend fun updateName(id: Long, name: String) {
        foodItemDao.updateName(id, name)
    }

    suspend fun updateProteins(id: Long, proteins: Short) {
        foodItemDao.updateProteins(id, proteins)
    }

    suspend fun updateFats(id: Long, fats: Short) {
        foodItemDao.updateFats(id, fats)
    }

    suspend fun updateCarbohydrates(id: Long, carbohydrates: Short) {
        foodItemDao.updateCarbohydrates(id, carbohydrates)
    }

    suspend fun updateWater(id: Long, water: Short) {
        foodItemDao.updateWater(id, water)
    }

    suspend fun updateCalories(id: Long, water: Short) {
        foodItemDao.updateCalories(id, water)
    }

    suspend fun updateNum(id: Long, num: Short) {
        foodItemDao.updateNum(id, num)
    }
}