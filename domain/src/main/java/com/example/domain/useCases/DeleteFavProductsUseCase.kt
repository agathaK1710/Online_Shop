package com.example.domain.useCases

import com.example.domain.repo.ShopRepository
import javax.inject.Inject

class DeleteFavProductsUseCase @Inject constructor(
    private val repository: ShopRepository
){
    suspend operator fun invoke() = repository.deleteFavProducts()
}