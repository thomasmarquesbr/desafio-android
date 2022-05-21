package com.thomas.domainlayer.base

import com.thomas.archtecture_framework.failure.FailureFactory
import com.thomas.archtecture_framework.wrapper.BaseError
import com.thomas.archtecture_framework.wrapper.ErrorWrapper

class FailureHandler<T : FailureFactory<BaseError>>(
        private val failure: T,
        private vararg val fieldsFailure: Pair<String, T> = arrayOf(),
) {
    fun transform(): (originalError: ErrorWrapper<ErrorDetailModel>) -> T {
        return { originalError ->
            val failure = originalError.errorBody

            val status = failure?.status
            when {
                status != null -> {
                    createFailure(this.failure.getFailure(status.toInt()) as T, failure)
                }
                else -> {
                    this.failure.getFailure(ErrorTranslate.GENERIC_FAILURE.code) as T
                }
            }
        }
    }

    private fun createFailure(failure: T, base: ErrorDetailModel?): T {
        failure.errorData = BaseError(
                code = base?.status?.toInt(),
                title = "",
                message = base?.message ?: ""
        )
        return failure
    }

}
