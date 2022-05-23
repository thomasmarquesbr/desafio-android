package com.thomas.datalayer.features.users.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomas.datalayer.features.users.local.model.UserEntity

@Dao
interface UsersDAO {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<UserEntity>)

    @Query("DELETE FROM user")
    fun deleteAll()

}
