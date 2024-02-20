package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<UserEntity>

    @Query("SELECT COUNT(*) FROM user")
    suspend fun getUserCount(): Int

    @Query("DELETE FROM user")
    suspend fun clearUserTable()
}