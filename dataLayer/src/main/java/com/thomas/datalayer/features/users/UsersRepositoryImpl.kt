package com.thomas.datalayer.features.users

import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.archtecture_framework.wrapper.ResultWrapper
import com.thomas.datalayer.extensions.transformError
import com.thomas.datalayer.features.users.local.UsersLocalDataSource
import com.thomas.datalayer.features.users.local.model.UserEntity
import com.thomas.datalayer.features.users.remote.UsersAPIDataSource
import com.thomas.datalayer.features.users.remote.model.UserDTO
import com.thomas.domainlayer.base.ErrorDetailModel
import com.thomas.domainlayer.features.users.UsersRepository
import com.thomas.domainlayer.features.users.model.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext

class UsersRepositoryImpl(
    private val usersAPIDataSource: UsersAPIDataSource,
    private val usersLocalDataSource: UsersLocalDataSource
): UsersRepository {

    override suspend fun getUsers(fromCache: Boolean): ResultWrapper<List<UserModel>, ErrorWrapper<ErrorDetailModel>> {
        return if (fromCache)
            usersLocalDataSource.getUsers()
                .transformSuccess { UserEntity.mapTo(it) }
                .transformError(transformError())
        else
            usersAPIDataSource.getUsers()
                .transformSuccess {
                    val listModel = UserDTO.mapFrom(it)
                    val listEntities = UserEntity.mapFrom(listModel)
                    usersLocalDataSource.saveUsers(listEntities)
                    listModel
                }
                .transformError(transformError())

    }
}
