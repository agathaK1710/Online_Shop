package com.example.onlineshop.ui.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInfo(
    val title: String,
    val value: String
) : Parcelable