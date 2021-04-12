package com.example.composediet.data

import androidx.room.*

@Entity(tableName = "Product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var foodItemId: Long,
    var name: String,
    var proteins: Short,
    var fats: Short,
    var carbohydrates: Short,
    var water: Short,
    var calories: Short,
    var num: Short
)

@Entity(primaryKeys = ["productId", "dishId"])
data class FoodItemDishCrossRef(
    var productId: Long,
    var dishId: Long
)

@Entity(tableName = "Dish")
data class Dish(
    @PrimaryKey(autoGenerate = true)
    var dishId: Long,
    var name: String,
    var proteins: Short,
    var fats: Short,
    var carbohydrates: Short,
    var water: Short,
    var calories: Short,
    var num: Short
)

data class ProductWithDishes(
    @Embedded var product: Product,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "productId",
        associateBy = Junction(FoodItemDishCrossRef::class)
    )
    var dish: List<Dish>
)

data class DishWithProducts(
    @Embedded var dish: Dish,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "productId",
        associateBy = Junction(FoodItemDishCrossRef::class)
    )
    var products: List<Product>
)