package com.example.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_products")
data class FavouriteEntity(
    @PrimaryKey val id: String
)