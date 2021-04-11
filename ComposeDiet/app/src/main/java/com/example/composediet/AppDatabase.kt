package com.example.composediet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.composediet.dao.DishDao
import com.example.composediet.dao.FoodItemDao
import com.example.composediet.data.Dish
import com.example.composediet.data.DishWithProducts
import com.example.composediet.data.FoodItem
import com.example.composediet.data.FoodItemDishCrossRef

@Database(entities = [FoodItem::class, Dish::class, FoodItemDishCrossRef::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dishDao(): DishDao
    abstract fun foodItemDao(): FoodItemDao

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