package com.example.domain.repo

import com.example.domain.entities.User

interface ShopRepository {
    suspend fun insertUser(user: User)
    suspend fun getUserCount(): Int
}