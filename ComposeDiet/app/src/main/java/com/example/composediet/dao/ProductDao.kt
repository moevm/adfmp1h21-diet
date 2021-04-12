package com.example.composediet.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.composediet.data.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAllProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Update
    suspend fun update(product: Product)
//
//    @Query("DELETE FROM Product WHERE name = :name")
//    suspend fun delete(name: String)
//
//    @Update
//    suspend fun update(foodItem: FoodItem)
//
//    @Query("UPDATE Product SET name = :name WHERE foodItemId = :id")
//    suspend fun updateName(id: Long, name: String)
//
//    @Query("UPDATE Product SET proteins = :proteins WHERE foodItemId = :id")
//    suspend fun updateProteins(id: Long, proteins: Short)
//
//    @Query("UPDATE Product SET fats = :fats WHERE foodItemId = :id")
//    suspend fun updateFats(id: Long, fats: Short)
//
//    @Query("UPDATE Product SET carbohydrates = :carbohydrates WHERE foodItemId = :id")
//    suspend fun updateCarbohydrates(id: Long, carbohydrates: Short)
//
//    @Query("UPDATE Product SET water = :water WHERE foodItemId = :id")
//    suspend fun updateWater(id: Long, water: Short)
//
//    @Query("UPDATE Product SET calories = :calories WHERE foodItemId = :id")
//    suspend fun updateCalories(id: Long, calories: Short)
//
//    @Query("UPDATE Product SET num = :num WHERE foodItemId = :id")
//    suspend fun updateNum(id: Long, num: Short)
//
    @Query("SELECT EXISTS(SELECT 1 FROM Product WHERE name = :name LIMIT 1)")
    suspend fun isExists(name: String): Boolean
}