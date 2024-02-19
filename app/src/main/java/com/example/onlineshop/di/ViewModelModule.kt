package com.example.onlineshop.di

import androidx.lifecycle.ViewModel
import com.example.onlineshop.ui.cart.CartViewModel
import com.example.onlineshop.ui.login.LoginViewModel
import com.example.onlineshop.ui.profile.ProfileViewModel
import com.example.onlineshop.ui.sale.SaleViewModel
import com.example.onlineshop.ui.catalog.CatalogViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindRecipeViewModule(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModule(viewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModule(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SaleViewModel::class)
    fun bindSaleViewModule(viewModel: SaleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    fun bindCatalogViewModule(viewModel: CatalogViewModel): ViewModel
}