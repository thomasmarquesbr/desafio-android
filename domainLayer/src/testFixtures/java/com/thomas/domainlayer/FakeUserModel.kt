package com.thomas.domainlayer

import com.thomas.domainlayer.features.users.model.UserModel

class FakeUserModel {
    companion object {
        fun mock() = UserModel(
            id = 10,
            name = "José da Silva",
            username = "@josedasilva",
            img = "https://url.image.com"
        )
    }
}
