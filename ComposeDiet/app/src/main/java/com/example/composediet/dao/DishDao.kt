package com.example.composediet.dao

import androidx.room.*
import com.example.composediet.data.Dish
import com.example.composediet.data.DishWithProducts
import com.example.composediet.data.FoodItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {
    @Transaction
    @Query("SELECT * FROM Dish")
    fun getDishWithProducts(): Flow<List<DishWithProducts>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dish: Dish)

    @Delete
    suspend fun delete(dish: Dish)

    @Update
    suspend fun update(dish: Dish)

    @Query("UPDATE Dish SET name = :name WHERE dishId = :id")
    suspend fun updateName(id: Long, name: String)

    @Query("UPDATE Dish SET num = :num WHERE dishId = :id")
    suspend fun updateNum(id: Long, num: Short)

    @Insert
    fun insertFoodItems(foodItems: List<FoodItem>)

    @Delete
    fun deleteFoodItems(foodItems: List<FoodItem>)

//    @Query
}