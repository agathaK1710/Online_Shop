package com.example.data.network.models

data class ProductDto(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: PriceDto,
    val feedback: FeedbackDto?,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<InfoDto>,
    val ingredients: String
)