package com.example.domain.entities

data class Product(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: Price,
    val feedback: Feedback? = null,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<Info>,
    val ingredients: String
)