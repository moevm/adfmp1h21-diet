package com.example.composediet.repositories

import com.example.composediet.dao.DishDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DishRepository @Inject constructor(private val dishDao: DishDao) {
    fun getAllDishes() = dishDao.getAllDishes()
    fun isIngredient() = dishDao.isIngredient()
}