package com.thomas.fakes

import com.thomas.datalayer.features.users.model.UserDTO

class FakeUserDTO {
    companion object {
        fun mock() = UserDTO(
            id = 10,
            name = "José da Silva",
            username = "@josedasilva",
            img = "https://url.image.com"
        )
    }
}
