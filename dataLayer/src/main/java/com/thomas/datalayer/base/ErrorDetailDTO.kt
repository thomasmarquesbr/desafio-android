package com.thomas.datalayer.base

import com.google.gson.annotations.SerializedName
import com.thomas.domainlayer.base.ErrorDetailModel

data class ErrorDetailDTO(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
) {
    fun mapTo() = ErrorDetailModel(
        message = this.message ?: "",
        status = this.status ?: ""
    )
}
