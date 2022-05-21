package com.thomas.archtecture_framework.wrapper

data class ResultWrapper<SUCCESS, ERROR>(
    val success: SUCCESS? = null,
    val error: ERROR? = null,
    val keyValueMap: MutableMap<String, String>? = null,
    val resultCode: Int? = null
) {

    fun <TO_SUCCESS, TO_ERROR> transform(
        mapperSuccessFunction: (originalSuccess: SUCCESS) -> TO_SUCCESS,
        mapperErrorFunction: (originalError: ERROR) -> TO_ERROR
    ): ResultWrapper<TO_SUCCESS, TO_ERROR> {

        return if (this.success != null) {
            ResultWrapper(
                success = mapperSuccessFunction.invoke(this.success),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            ResultWrapper(
                error = this.error?.let { mapperErrorFunction.invoke(it) },
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }

    fun <TO_SUCCESS> transformSuccess(
        mapperFunction: (originalSuccess: SUCCESS) -> TO_SUCCESS
    ): ResultWrapper<TO_SUCCESS, ERROR> {
        return if (this.success != null) {
            ResultWrapper(
                success = mapperFunction.invoke(this.success),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            ResultWrapper(
                error = this.error,
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }

    fun <TO_ERROR> transformError(
        mapperFunction: (originalError: ERROR) -> TO_ERROR
    ): ResultWrapper<SUCCESS, TO_ERROR> {
        return if (this.success != null) {
            ResultWrapper(
                success = this.success,
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            ResultWrapper(
                error = this.error?.let { mapperFunction.invoke(it) },
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }

    companion object {
        fun <SUCCESS, ERROR> createSuccess(
            success: SUCCESS,
            resultCode: Int = ResultCode.HTTP_SUCCESS_OK
        ) = ResultWrapper<SUCCESS, ERROR>(
            success = success,
            resultCode = resultCode
        )

        fun <SUCCESS, ERROR> createError(
            error: ERROR,
            resultCode: Int = ResultCode.APP_GENERIC_ERROR
        ) = ResultWrapper<SUCCESS, ERROR>(
            error = error,
            resultCode = resultCode
        )
    }

    fun isSuccess() =
        resultCode != null && resultCode < ResultCode.HTTP_REDIRECTION_MULTIPLE_OPTIONS

    fun isError() = !isSuccess()
}
