package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.ProductDao
import com.example.data.db.dao.UserDao
import com.example.data.db.entities.FavouriteEntity
import com.example.data.db.entities.UserEntity

@Database(
    version = 2,
    entities = [
        UserEntity::class,
        FavouriteEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "onlineShopApp.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun getUserDao(): UserDao
    abstract fun getProductDao(): ProductDao
}