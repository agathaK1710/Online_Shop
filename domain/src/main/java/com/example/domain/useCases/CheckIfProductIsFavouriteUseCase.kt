package com.example.domain.useCases

import com.example.domain.repo.ShopRepository
import javax.inject.Inject

class CheckIfProductIsFavouriteUseCase @Inject constructor(
    private val repository: ShopRepository
){
    suspend operator fun invoke(productId:String) = repository.checkIfProductIsFavourite(productId)
}