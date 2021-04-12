package com.example.composediet.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.composediet.data.Dish
import com.example.composediet.data.DishWithProducts
import com.example.composediet.data.Product

@Dao
interface DishDao {
    @Query("SELECT * FROM Dish")
    fun getAllDishes(): LiveData<List<Dish>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(dish: Dish)
//
//    @Delete
//    suspend fun delete(dish: Dish)
//
//    @Update
//    suspend fun update(dish: Dish)
//
//    @Query("UPDATE Dish SET name = :name WHERE dishId = :id")
//    suspend fun updateName(id: Long, name: String)
//
//    @Query("UPDATE Dish SET num = :num WHERE dishId = :id")
//    suspend fun updateNum(id: Long, num: Short)
//
//    @Insert
//    suspend fun insertFoodItems(products: List<Product>)
//
//    @Delete
//    suspend fun deleteFoodItems(products: List<Product>)
//
//    @Query("SELECT EXISTS(SELECT 1 FROM Dish WHERE name = :name LIMIT 1)")
//    fun isExists(name: String): Boolean
}