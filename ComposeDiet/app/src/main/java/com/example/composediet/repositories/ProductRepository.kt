package com.example.composediet.repositories

import com.example.composediet.dao.ProductDao
import com.example.composediet.data.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val productDao: ProductDao) {
    fun getAllProducts() = productDao.getAllProducts()
    suspend fun insertProduct(product: Product) = productDao.insert(product)
    suspend fun deleteProduct(product: Product) = productDao.delete(product)
    suspend fun updateProduct(product: Product) = productDao.delete(product)
}