package com.picpay.desafio.android.features.users.model

import android.os.Parcelable
import com.thomas.domainlayer.features.users.model.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
class UserUIModel(
    val img: String = "",
    val name: String = "",
    val id: Int = 0,
    val username: String = ""
): Parcelable {

    fun mapFrom(userModel: UserModel) = UserUIModel(
        img = userModel.img,
        name = userModel.name,
        id = userModel.id,
        username = userModel.username
    )

    companion object {
        fun mapFrom(listModel: List<UserModel>): List<UserUIModel> {
            return listModel.map { UserUIModel().mapFrom(it) }
        }
    }

}
