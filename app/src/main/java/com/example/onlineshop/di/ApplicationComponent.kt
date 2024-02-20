package com.example.onlineshop.di

import android.app.Application
import com.example.onlineshop.MainActivity
import com.example.onlineshop.ui.cart.CartFragment
import com.example.onlineshop.ui.login.LoginFragment
import com.example.onlineshop.ui.main.MainFragment
import com.example.onlineshop.ui.nav.NavigationFragment
import com.example.onlineshop.ui.profile.ProfileFragment
import com.example.onlineshop.ui.sale.SaleFragment
import com.example.onlineshop.ui.catalog.CatalogFragment
import com.example.onlineshop.ui.product.ProductPageFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, DataModule::class]
)
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: CartFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: MainFragment)
    fun inject(fragment: NavigationFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: SaleFragment)
    fun inject(fragment: CatalogFragment)
    fun inject(fragment: ProductPageFragment)

    @Component.Factory
    interface ApplicationFactory {
        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}