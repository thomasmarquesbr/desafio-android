package com.thomas.datalayer.features.users

import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.archtecture_framework.wrapper.ResultWrapper
import com.thomas.datalayer.extensions.transformError
import com.thomas.domainlayer.base.ErrorDetailModel
import com.thomas.domainlayer.features.users.UsersRepository
import com.thomas.domainlayer.features.users.model.UserModel

class UsersRepositoryImpl(
    private val usersAPIDataSource: UsersAPIDataSource
): UsersRepository {

    override suspend fun getUsers(): ResultWrapper<List<UserModel>, ErrorWrapper<ErrorDetailModel>> {
        return usersAPIDataSource.getUsers()
            .transformSuccess { listDto ->
                listDto.map { it.mapTo() }
            }
            .transformError(transformError())
    }
}
