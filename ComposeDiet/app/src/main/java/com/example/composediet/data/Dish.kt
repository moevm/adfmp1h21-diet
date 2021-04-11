package com.example.composediet.data

import androidx.room.*

@Entity(primaryKeys = ["foodItemId", "dishId"])
data class FoodItemDishCrossRef(
    var foodItemId: Long,
    var dishId: Long
)

@Entity(tableName = "Dish")
data class Dish(
    @PrimaryKey(autoGenerate = true) var dishId: Long,
    var name: String,
    var num: Short
)

data class ProductWithDishes(
    @Embedded var foodItem: FoodItem,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "foodItemId",
        associateBy = Junction(FoodItemDishCrossRef::class)
    )
    var dish: Set<Dish>
)

data class DishWithProducts(
    @Embedded var dish: Dish,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "foodItemId",
        associateBy = Junction(FoodItemDishCrossRef::class)
    )
    var foodItems: Set<FoodItem>
)