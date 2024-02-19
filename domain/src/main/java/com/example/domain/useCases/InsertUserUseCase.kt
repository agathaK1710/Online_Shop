package com.example.domain.useCases

import com.example.domain.entities.User
import com.example.domain.repo.ShopRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: ShopRepository
){
    suspend operator fun invoke(user: User) = repository.insertUser(user)
}