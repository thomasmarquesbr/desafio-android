package com.picpay.desafio.android.fakes

import com.picpay.desafio.android.features.users.model.UserUIModel

class FakeUserUIModel {
    companion object {
        fun mock() = UserUIModel(
            id = 10,
            name = "José da Silva",
            username = "@josedasilva",
            img = "https://url.image.com"
        )
    }
}
