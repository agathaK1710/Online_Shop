package com.example.onlineshop.ui.catalog.recyclerView

import android.os.Parcelable
import com.example.onlineshop.ui.product.ProductInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductCard(
    val id: String,
    val available: Int,
    val description: String,
    val info: List<ProductInfo>,
    val ingredients: String,
    val images: List<Int>,
    val oldPrice: String,
    val newPrice: String,
    val unit: String,
    val discount: Int,
    val title: String,
    val subtitle: String,
    val rating: Double? = null,
    val feedbackCount: Int? = null,
    val tags: List<String>,
    var isFavourite: Boolean
): DisplayableItem, Parcelable