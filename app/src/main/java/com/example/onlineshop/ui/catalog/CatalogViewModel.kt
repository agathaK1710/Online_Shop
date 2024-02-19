package com.example.onlineshop.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.ProductList
import com.example.domain.useCases.GetProductsUseCase
import com.example.onlineshop.ui.catalog.recyclerView.ProductCard
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    getProductsUseCase: GetProductsUseCase,
    catalogMapper: CatalogMapper
) : ViewModel() {

    private val _productsList = MutableLiveData<ProductList>()

    private val _productsCardsList = MutableLiveData<List<ProductCard>>()
    val productsCardsList: LiveData<List<ProductCard>> = _productsCardsList

    private val _allProducts = MutableLiveData<List<ProductCard>>()


    init {
        viewModelScope.launch {
            _productsList.value = getProductsUseCase()
            _productsCardsList.value =
                _productsList.value?.let { catalogMapper.mapProductListToProductCardList(it) }
            sortBy(SortCriteria.RATING)
            _allProducts.value =
                _productsList.value?.let { catalogMapper.mapProductListToProductCardList(it) }
        }
    }

    fun sortBy(criteria: SortCriteria) {
        when (criteria) {
            SortCriteria.RATING -> {
                _productsCardsList.value = _productsCardsList.value?.sortedByDescending {
                    it.feedback?.rating
                }
            }

            SortCriteria.PRICE_DESC -> {
                _productsCardsList.value = _productsCardsList.value?.sortedByDescending {
                    it.newPrice.toInt()
                }
            }

            SortCriteria.PRICE_ASC -> {
                _productsCardsList.value = _productsCardsList.value?.sortedBy {
                    it.newPrice.toInt()
                }
            }
        }
    }

    fun filterList(tag: Tag) {
        if(tag == Tag.WATCH_ALL) {
            _productsCardsList.value = _allProducts.value
        } else {
            _productsCardsList.value = _allProducts.value?.filter {
                it.tags.contains(tag.tagName)
            }
        }
    }

    fun resetTag(){
        _productsCardsList.value = _allProducts.value
    }
}