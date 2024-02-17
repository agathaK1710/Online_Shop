package com.example.data

import com.example.data.db.dao.UserDao
import com.example.data.mapper.UserMapper
import com.example.domain.entities.User
import com.example.domain.repo.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val mapper: UserMapper,
    private val userDao: UserDao
) : ShopRepository {
    override suspend fun insertUser(user: User) {
        userDao.insertUser(mapper.mapUserToUserEntity(user))
    }

    override suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }
}