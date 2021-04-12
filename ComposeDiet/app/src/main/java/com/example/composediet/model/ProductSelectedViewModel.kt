package com.example.composediet.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composediet.data.Product

class ProductSelectedViewModel: ViewModel() {
    private val _selected = MutableLiveData<Product>()
    val selected: LiveData<Product> = _selected

    fun onSelectedChange(product: Product?) {
        if (product != null) {
            _selected.value = product
        }
        else {
            _selected.value = Product(-1, "", 0, 0, 0, 0, 0, 0)
        }
    }
}