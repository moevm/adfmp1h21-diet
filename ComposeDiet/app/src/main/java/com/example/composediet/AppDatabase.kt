package com.example.composediet

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composediet.dao.DishDao
import com.example.composediet.dao.ProductDao
import com.example.composediet.data.Dish
import com.example.composediet.data.Product
import com.example.composediet.data.FoodItemDishCrossRef

@Database(entities = [Product::class, Dish::class, FoodItemDishCrossRef::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dishDao(): DishDao
    abstract fun foodItemDao(): ProductDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "AppDatabase"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//
//        }
//    }

}