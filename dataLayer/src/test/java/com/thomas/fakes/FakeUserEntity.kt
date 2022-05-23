package com.thomas.fakes

import com.thomas.datalayer.features.users.local.model.UserEntity

class FakeUserEntity {
    companion object {
        fun mock() = UserEntity(
            id = 10,
            name = "José da Silva",
            username = "@josedasilva",
            img = "https://url.image.com"
        )
    }
}
