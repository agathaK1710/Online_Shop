package com.example.domain.useCases

import com.example.domain.entities.FavouriteProduct
import com.example.domain.repo.ShopRepository
import javax.inject.Inject

class DeleteFavouriteProductUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend operator fun invoke(product: FavouriteProduct) =
        repository.deleteFavouriteProduct(product)
}