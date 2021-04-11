package com.example.composediet.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.composediet.dao.FoodItemDao
import com.example.composediet.data.FoodItem
import kotlinx.coroutines.flow.Flow

class FoodItemRepository(private val foodItemDao: FoodItemDao) {
    val foodItems: Flow<Set<FoodItem>> = foodItemDao.getAllFoodItems()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(foodItem: FoodItem) {
        foodItemDao.insert(foodItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(name: String) {
        foodItemDao.delete(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(foodItem: FoodItem) {
        foodItemDao.update(foodItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateName(id: ULong, name: String) {
        foodItemDao.updateName(id, name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateProteins(id: ULong, proteins: Short) {
        foodItemDao.updateProteins(id, proteins)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateFats(id: ULong, fats: Short) {
        foodItemDao.updateFats(id, fats)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCarbohydrates(id: ULong, carbohydrates: Short) {
        foodItemDao.updateCarbohydrates(id, carbohydrates)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateWater(id: ULong, water: Short) {
        foodItemDao.updateWater(id, water)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCalories(id: ULong, water: Short) {
        foodItemDao.updateCalories(id, water)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateNum(id: ULong, num: Short) {
        foodItemDao.updateNum(id, num)
    }
}