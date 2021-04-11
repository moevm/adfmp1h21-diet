package com.example.composediet.repositories

import androidx.annotation.WorkerThread
import com.example.composediet.dao.DishDao
import com.example.composediet.data.Dish
import com.example.composediet.data.DishWithProducts
import com.example.composediet.data.FoodItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DishRepository
    @Inject constructor(private val dishDao: DishDao) {
    val dishes: Flow<List<DishWithProducts>> = dishDao.getDishWithProducts()

    suspend fun insert(dish: Dish) {
        dishDao.insert(dish)
    }

    suspend fun insertFoodItems(foodItems: List<FoodItem>) {
        dishDao.insertFoodItems(foodItems)
    }

    suspend fun deleteFoodItems(foodItems: List<FoodItem>) {
        dishDao.deleteFoodItems(foodItems)
    }

    suspend fun updateName(id: Long, name: String) {
        dishDao.updateName(id, name)
    }

    suspend fun updateNum(id: Long, num: Short) {
        dishDao.updateNum(id, num)
    }
}