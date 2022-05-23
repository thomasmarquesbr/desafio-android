package com.thomas.datalayer.features.users.local

import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.archtecture_framework.wrapper.ResultWrapper
import com.thomas.datalayer.base.BaseDataSourceImpl
import com.thomas.datalayer.base.ErrorDetailDTO
import com.thomas.datalayer.di.AppDatabase
import com.thomas.datalayer.features.users.local.model.UserEntity

class UsersLocalDataSource(
    private val database: AppDatabase
): BaseDataSourceImpl() {

    suspend fun getUsers(): ResultWrapper<List<UserEntity>, ErrorWrapper<ErrorDetailDTO>>  {
        return safeDatabasaCall {
            database.userDao().getAll()
        }
    }

    fun saveUsers(list: List<UserEntity>) {
        database.userDao().run {
            deleteAll()
            insertAll(list)
        }
    }
}
