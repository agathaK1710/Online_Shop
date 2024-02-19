package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entities.FavouriteEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteProduct(favouriteProduct: FavouriteEntity)

    @Delete
    suspend fun deleteFavouriteProduct(favouriteProduct: FavouriteEntity)

    @Query("SELECT id FROM favourite_products WHERE id == :productId")
    suspend fun checkIfProductIsFavourite(productId: String): String?
}