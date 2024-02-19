package com.example.data

import com.example.data.db.dao.ProductDao
import com.example.data.db.dao.UserDao
import com.example.data.mapper.ProductMapper
import com.example.data.mapper.UserMapper
import com.example.data.network.ApiService
import com.example.domain.entities.FavouriteProduct
import com.example.domain.entities.ProductList
import com.example.domain.entities.User
import com.example.domain.repo.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val userMapper: UserMapper,
    private val productMapper: ProductMapper,
    private val userDao: UserDao,
    private val productDao: ProductDao,
    private val apiService: ApiService
) : ShopRepository {
    override suspend fun insertUser(user: User) {
        userDao.insertUser(userMapper.mapUserToUserEntity(user))
    }

    override suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }

    override suspend fun getProductList(): ProductList {
        return productMapper.mapProductListDtoToProductList(apiService.getProducts())
    }

    override suspend fun insertProductToFavourites(favouriteProduct: FavouriteProduct) {
        productDao.insertFavouriteProduct(productMapper.mapFavouriteProductToEntity(favouriteProduct))
    }

    override suspend fun deleteFavouriteProduct(favouriteProduct: FavouriteProduct) {
        productDao.deleteFavouriteProduct(productMapper.mapFavouriteProductToEntity(favouriteProduct))
    }

    override suspend fun checkIfProductIsFavourite(productId: String): String? {
        return productDao.checkIfProductIsFavourite(productId)
    }
}