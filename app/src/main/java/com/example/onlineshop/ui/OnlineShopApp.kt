package com.example.onlineshop.ui

import android.app.Application
import com.example.onlineshop.di.DaggerApplicationComponent

class OnlineShopApp: Application() {
    val component by lazy{
        DaggerApplicationComponent.factory().create(this)
    }
}