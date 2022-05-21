package com.thomas.datalayer.extensions

import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.datalayer.base.ErrorDetailDTO
import com.thomas.domainlayer.base.ErrorDetailModel

fun transformError(): (errorDetailDTO: ErrorWrapper<ErrorDetailDTO>) -> ErrorWrapper<ErrorDetailModel> {
    return {
        ErrorWrapper(
                errorBody = it.errorBody?.mapTo(),
                technicalErrorMessage = it.errorBody?.message
        )
    }
}
