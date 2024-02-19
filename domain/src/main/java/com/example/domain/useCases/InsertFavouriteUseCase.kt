package com.example.domain.useCases

import com.example.domain.entities.FavouriteProduct
import com.example.domain.repo.ShopRepository
import javax.inject.Inject

class InsertFavouriteUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend operator fun invoke(product: FavouriteProduct) =
        repository.insertProductToFavourites(product)
}