package com.example.onlineshop.ui.catalog

import com.example.domain.entities.Product
import com.example.domain.entities.ProductList
import com.example.onlineshop.R
import com.example.onlineshop.ui.catalog.recyclerView.ProductCard
import javax.inject.Inject

class CatalogMapper @Inject constructor() {
    fun mapProductToProductCard(product: Product, isFavourite: Boolean) =
        ProductCard(
            id = product.id,
            newPrice = product.price.priceWithDiscount,
            oldPrice = product.price.price,
            discount = product.price.discount,
            unit = product.price.unit,
            title = product.title,
            subtitle = product.subtitle,
            feedback = product.feedback,
            rating = product.feedback?.rating,
            images = getImagesList(product.id),
            tags = product.tags,
            isFavourite = isFavourite
        )

}

private fun getImagesList(id: String): List<Int> = when (id) {
    "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(R.drawable.image_6, R.drawable.image_5)
    "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(R.drawable.image_1, R.drawable.image_2)
    "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(R.drawable.image_5, R.drawable.image_6)
    "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.image_3, R.drawable.image_4)
    "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.image_2, R.drawable.image_3)
    "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.image_6, R.drawable.image_1)
    "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.image_4, R.drawable.image_3)
    "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.image_1, R.drawable.image_5)
    else -> emptyList()
}