package com.example.onlineshop.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.ProductList
import com.example.domain.useCases.GetProductsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _productsList = MutableLiveData<ProductList>()
    val productsList: LiveData<ProductList> = _productsList
    init {
        viewModelScope.launch {
            _productsList.value = getProductsUseCase()
        }
    }
}