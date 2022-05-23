package com.thomas.domainlayer.features.users

import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.archtecture_framework.wrapper.ResultWrapper
import com.thomas.domainlayer.base.ErrorDetailModel
import com.thomas.domainlayer.features.users.model.UserModel

interface UsersRepository {
    suspend fun getUsers(fromCache: Boolean): ResultWrapper<List<UserModel>, ErrorWrapper<ErrorDetailModel>>
}
