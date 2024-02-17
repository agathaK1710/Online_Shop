package com.example.data.mapper

import com.example.data.db.entities.UserEntity
import com.example.domain.entities.User
import javax.inject.Inject

class UserMapper @Inject constructor(){
    fun mapUserEntityToUser(userEntity: UserEntity) = User(
        name = userEntity.name,
        surname = userEntity.surname,
        phoneNumber = userEntity.phoneNum
    )

    fun mapUserToUserEntity(user:User) = UserEntity(
        name = user.name,
        surname = user.surname,
        phoneNum = user.phoneNumber
    )
}