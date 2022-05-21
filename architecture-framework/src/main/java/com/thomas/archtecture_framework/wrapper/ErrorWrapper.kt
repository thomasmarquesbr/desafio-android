package com.thomas.archtecture_framework.wrapper

data class ErrorWrapper<ERROR_BODY>(
    val errorBody: ERROR_BODY? = null,
    val technicalErrorMessage: String? = null
) {
    override fun toString(): String {
        return "TechnicalErrorMessage: $technicalErrorMessage - ErrorBody: ${errorBody?.toString()}"
    }
}
