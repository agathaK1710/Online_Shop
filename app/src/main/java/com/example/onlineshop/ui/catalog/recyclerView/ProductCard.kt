package com.example.onlineshop.ui.catalog.recyclerView

import com.example.domain.entities.Feedback

data class ProductCard(
    val id: String,
    val images: List<Int>,
    val oldPrice: String,
    val newPrice: String,
    val unit: String,
    val discount: Int,
    val title: String,
    val subtitle: String,
    val rating: Double? = null,
    val feedback: Feedback? = null,
    val tags: List<String>,
    var isFavourite: Boolean
): DisplayableItem