package com.example.composediet.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.composediet.data.Dish
import com.example.composediet.data.Product
import com.example.composediet.repositories.DishRepository
import com.example.composediet.repositories.ProductRepository
import javax.inject.Inject

class FoodViewModel
@Inject internal constructor(
    private val productRepository: ProductRepository,
    private val dishRepository: DishRepository
) : ViewModel() {
    val products: LiveData<List<Product>> = productRepository.getAllProducts()
    val dishes: LiveData<List<Dish>> = dishRepository.getAllDishes()

}
