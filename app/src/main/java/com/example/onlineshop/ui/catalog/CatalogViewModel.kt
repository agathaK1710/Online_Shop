package com.example.onlineshop.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.FavouriteProduct
import com.example.domain.entities.ProductList
import com.example.domain.useCases.CheckIfProductIsFavouriteUseCase
import com.example.domain.useCases.DeleteFavouriteProductUseCase
import com.example.domain.useCases.GetProductsUseCase
import com.example.domain.useCases.InsertFavouriteUseCase
import com.example.onlineshop.ui.catalog.recyclerView.ProductCard
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val insertFavouriteUseCase: InsertFavouriteUseCase,
    private val checkIfProductIsFavouriteUseCase: CheckIfProductIsFavouriteUseCase,
    private val deleteFavouriteProductUseCase: DeleteFavouriteProductUseCase,
    private val catalogMapper: CatalogMapper
) : ViewModel() {

    private val _productsList = MutableLiveData<ProductList>()

    private val _productsCardsList = MutableLiveData<List<ProductCard>>()
    val productsCardsList: LiveData<List<ProductCard>> = _productsCardsList

    private val _allProducts = MutableLiveData<List<ProductCard>>()


    init {
        viewModelScope.launch {
            refreshProducts()
        }
    }

    fun refreshProducts() = viewModelScope.launch {
        _productsList.value = getProductsUseCase()
        _productsCardsList.value = getInitialList()
        sortBy(SortCriteria.RATING)
        _allProducts.value = getInitialList()
    }

    private suspend fun getInitialList() =
        _productsList.value?.items?.map {
            if (checkIfProductIsFavouriteUseCase(it.id) != null) {
                catalogMapper.mapProductToProductCard(it, true)
            } else {
                catalogMapper.mapProductToProductCard(it, false)
            }
        }

    fun sortBy(criteria: SortCriteria) {
        when (criteria) {
            SortCriteria.RATING -> {
                _productsCardsList.value = _productsCardsList.value?.sortedByDescending {
                    it.rating
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
        if (tag == Tag.WATCH_ALL) {
            _productsCardsList.value = _allProducts.value
        } else {
            _productsCardsList.value = _allProducts.value?.filter {
                it.tags.contains(tag.tagName)
            }
        }
    }

    fun resetTag() {
        _productsCardsList.value = _allProducts.value
    }

    fun addToFavourites(id: String) = viewModelScope.launch {
        insertFavouriteUseCase(FavouriteProduct(id))
        _productsCardsList.value = _productsCardsList.value?.map {
            if (it.id == id) it.isFavourite = true
            it
        }
    }

    fun deleteFavouriteProduct(id: String) = viewModelScope.launch {
        deleteFavouriteProductUseCase(FavouriteProduct(id))
        _productsCardsList.value = _productsCardsList.value?.map {
            if (it.id == id) it.isFavourite = false
            it
        }
    }
}