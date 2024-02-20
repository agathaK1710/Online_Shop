package com.example.onlineshop.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.FavouriteProduct
import com.example.domain.useCases.DeleteFavouriteProductUseCase
import com.example.domain.useCases.GetFavListUseCase
import com.example.domain.useCases.GetProductsUseCase
import com.example.domain.useCases.InsertFavouriteUseCase
import com.example.onlineshop.ui.catalog.CatalogMapper
import com.example.onlineshop.ui.catalog.recyclerView.ProductCard
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val getFavListUseCase: GetFavListUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val catalogMapper: CatalogMapper,
    private val insertFavouriteUseCase: InsertFavouriteUseCase,
    private val deleteFavouriteProductUseCase: DeleteFavouriteProductUseCase,
) : ViewModel(){

    private val _favList = MutableLiveData<List<ProductCard>>()
    val favList: LiveData<List<ProductCard>> = _favList

    init {
        viewModelScope.launch {
            refreshFavs()
        }
    }

    fun addToFavourites(id: String) = viewModelScope.launch {
        insertFavouriteUseCase(FavouriteProduct(id))
        _favList.value = _favList.value?.map {
            if (it.id == id) it.isFavourite = true
            it
        }
    }

    fun deleteFavouriteProduct(id: String) = viewModelScope.launch {
        deleteFavouriteProductUseCase(FavouriteProduct(id))
        _favList.value = _favList.value?.filter {
            it.id != id
        }
    }

    fun refreshFavs() = viewModelScope.launch {
        val favListIds = getFavListUseCase().map { it.id }
        val products = getProductsUseCase()
        val productCardList = products.items.map{
            catalogMapper.mapProductToProductCard(it, true)
        }
        _favList.value = productCardList.filter {product ->
            favListIds.contains(product.id)
        }
    }

    fun setBrandsList() {
        _favList.value = emptyList()
    }
}