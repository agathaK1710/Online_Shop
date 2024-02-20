package com.example.data.mapper

import com.example.data.db.entities.FavouriteEntity
import com.example.data.network.models.FeedbackDto
import com.example.data.network.models.InfoDto
import com.example.data.network.models.PriceDto
import com.example.data.network.models.ProductDto
import com.example.data.network.models.ProductListDto
import com.example.domain.entities.FavouriteProduct
import com.example.domain.entities.Feedback
import com.example.domain.entities.Info
import com.example.domain.entities.Price
import com.example.domain.entities.Product
import com.example.domain.entities.ProductList
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun mapFavProductEntityListToFavProductList(favProductEntityList: List<FavouriteEntity>) =
        favProductEntityList.map {
            mapFavouriteEntityToProduct(it)
        }

    fun mapFavouriteProductToEntity(favouriteProduct: FavouriteProduct) = FavouriteEntity(
        id = favouriteProduct.id
    )

    fun mapFavouriteEntityToProduct(favouriteEntity: FavouriteEntity) = FavouriteProduct(
        id = favouriteEntity.id
    )

    fun mapProductListDtoToProductList(productListDto: ProductListDto) = ProductList(
        items = mapProductDtoListToProductList(productListDto.items)
    )

    private fun mapProductDtoListToProductList(productDtoList: List<ProductDto>) =
        productDtoList.map {
            mapProductDtoToProduct(it)
        }

    private fun mapProductDtoToProduct(productDto: ProductDto) = Product(
        id = productDto.id,
        title = productDto.title,
        subtitle = productDto.subtitle,
        price = mapPriceDtoToPrice(productDto.price),
        feedback = mapFeedbackDtoToFeedback(productDto.feedback),
        tags = productDto.tags,
        available = productDto.available,
        description = productDto.description,
        info = mapListInfoDtoToListInfo(productDto.info),
        ingredients = productDto.ingredients
    )

    private fun mapPriceDtoToPrice(priceDto: PriceDto) = Price(
        price = priceDto.price,
        discount = priceDto.discount,
        priceWithDiscount = priceDto.priceWithDiscount,
        unit = priceDto.unit
    )

    private fun mapFeedbackDtoToFeedback(feedbackDto: FeedbackDto?): Feedback? {
        return if (feedbackDto != null) Feedback(
            count = feedbackDto.count,
            rating = feedbackDto.rating
        ) else null
    }

    private fun mapListInfoDtoToListInfo(infoDtoList: List<InfoDto>) = infoDtoList.map { infoDto ->
        Info(
            title = infoDto.title,
            value = infoDto.value
        )
    }
}