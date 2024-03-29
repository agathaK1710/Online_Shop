package com.example.domain.repo

import com.example.domain.entities.FavouriteProduct
import com.example.domain.entities.ProductList
import com.example.domain.entities.User

interface ShopRepository {
    suspend fun insertUser(user: User)
    suspend fun getUsers(): List<User>
    suspend fun deleteUsers()
    suspend fun getUserCount(): Int
    suspend fun getProductList(): ProductList
    suspend fun insertProductToFavourites(favouriteProduct: FavouriteProduct)
    suspend fun deleteFavouriteProduct(favouriteProduct: FavouriteProduct)
    suspend fun checkIfProductIsFavourite(productId: String): String?
    suspend fun getFavProducts(): List<FavouriteProduct>
    suspend fun deleteFavProducts()

}