package com.example.data.network.models

data class PriceDto(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)