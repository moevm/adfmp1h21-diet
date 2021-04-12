package com.example.composediet.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.composediet.data.Product
import com.example.composediet.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ProductViewModel
    @Inject internal constructor(val product: Product, private val productRepository: ProductRepository) :ViewModel() {

    fun onAddProduct() = viewModelScope.launch {
        productRepository.insertProduct(product)
    }
    fun onDeleteProduct() = viewModelScope.launch {
        productRepository.deleteProduct(product)
    }

    fun onUpdateProduct() = viewModelScope.launch {
        productRepository.updateProduct(product)
    }
}