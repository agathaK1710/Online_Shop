package com.example.domain.entities

data class Price(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)