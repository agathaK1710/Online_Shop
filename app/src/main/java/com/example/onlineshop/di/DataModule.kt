package com.example.onlineshop.di

import android.app.Application
import com.example.data.ShopRepositoryImpl
import com.example.data.db.AppDatabase
import com.example.data.db.dao.ProductDao
import com.example.data.db.dao.UserDao
import com.example.data.network.ApiFactory
import com.example.data.network.ApiService
import com.example.domain.repo.ShopRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun bindRecipeRepository(impl: ShopRepositoryImpl): ShopRepository
    companion object {
        @Provides
        fun provideRecipeDao(
            application: Application
        ): UserDao {
            return AppDatabase.getInstance(application).getUserDao()
        }

        @Provides
        fun provideProductDao(
            application: Application
        ): ProductDao {
            return AppDatabase.getInstance(application).getProductDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}