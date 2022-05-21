package com.thomas.datalayer.features.users.model

import com.google.gson.annotations.SerializedName
import com.thomas.domainlayer.features.users.model.UserModel

class UserDTO(
    @SerializedName("img")
    val img: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?
) {
    fun mapTo() = UserModel(
        img = this.img ?: "",
        name = this.name ?: "",
        id = this.id ?: 0,
        username = this.username ?: ""
    )
}