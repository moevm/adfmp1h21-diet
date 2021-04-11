package com.example.composediet.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.composediet.data.FoodItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodItemDao {

    @Query("SELECT * FROM Product")
    fun getAllFoodItems(): Flow<Set<FoodItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodItem: FoodItem)

    @Query("DELETE FROM Product WHERE name = :name")
    suspend fun delete(name: String)

    @Update
    suspend fun update(foodItem: FoodItem)

    @Query("UPDATE Product SET name = :name WHERE id = :id")
    suspend fun updateName(id: ULong, name: String)

    @Query("UPDATE Product SET proteins = :proteins WHERE id = :id")
    suspend fun updateProteins(id: ULong, proteins: Short)

    @Query("UPDATE Product SET fats = :fats WHERE id = :id")
    suspend fun updateFats(id: ULong, fats: Short)

    @Query("UPDATE Product SET carbohydrates = :carbohydrates WHERE id = :id")
    suspend fun updateCarbohydrates(id: ULong, carbohydrates: Short)

    @Query("UPDATE Product SET water = :water WHERE id = :id")
    suspend fun updateWater(id: ULong, water: Short)

    @Query("UPDATE Product SET calories = :calories WHERE id = :id")
    suspend fun updateCalories(id: ULong, calories: Short)

    @Query("UPDATE Product SET num = :num WHERE id = :id")
    suspend fun updateNum(id: ULong, num: Short)
}