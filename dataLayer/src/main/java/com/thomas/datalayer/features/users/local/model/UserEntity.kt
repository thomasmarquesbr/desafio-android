package com.thomas.datalayer.features.users.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thomas.domainlayer.features.users.model.UserModel

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "img")
    val img: String,
    @ColumnInfo(name ="name")
    val name: String,
    @ColumnInfo(name ="username")
    val username: String
) {

    fun mapTo() = UserModel(
        id = this.id,
        img = this.img,
        name = this.name,
        username = this.username
    )

    companion object {
        private fun mapFrom(userModel: UserModel) = UserEntity(
            id = userModel.id,
            img = userModel.img,
            name = userModel.name,
            username = userModel.username
        )

        fun mapFrom(listModel: List<UserModel>) = listModel
            .map { mapFrom(it) }

        fun mapTo(listEntity: List<UserEntity>) = listEntity
            .map { it.mapTo() }
    }
}
