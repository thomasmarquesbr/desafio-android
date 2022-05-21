package com.thomas.datalayer.features.users

import com.thomas.datalayer.features.users.model.UserDTO
import retrofit2.Response
import retrofit2.http.GET

interface UsersAPI {

    @GET("users")
    fun getUsers(): Response<List<UserDTO>>

}
