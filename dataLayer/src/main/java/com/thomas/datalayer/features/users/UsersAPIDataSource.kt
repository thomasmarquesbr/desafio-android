package com.thomas.datalayer.features.users

import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.archtecture_framework.wrapper.ResultWrapper
import com.thomas.datalayer.base.BaseDataSourceImpl
import com.thomas.datalayer.base.ErrorDetailDTO
import com.thomas.datalayer.features.users.model.UserDTO

class UsersAPIDataSource(
    private val usersAPI: UsersAPI
): BaseDataSourceImpl() {

    suspend fun getUsers(): ResultWrapper<List<UserDTO>, ErrorWrapper<ErrorDetailDTO>> {
        return safeApiCall {
            usersAPI.getUsers()
        }
    }

}
