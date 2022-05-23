package com.thomas.archtecture_framework.fakes

import com.thomas.archtecture_framework.wrapper.ResultCode
import com.thomas.archtecture_framework.wrapper.ResultWrapper

class FakeResultWrapper {

    companion object {
        fun <SUCCESS, ERROR> mockSuccess(success: SUCCESS): ResultWrapper<SUCCESS, ERROR> {
            return ResultWrapper.createSuccess(
                    success = success
            )
        }

        fun <SUCCESS, ERROR> mockError(error: ERROR): ResultWrapper<SUCCESS, ERROR> {
            return ResultWrapper.createError(
                    error = error,
                    resultCode = ResultCode.APP_GENERIC_ERROR
            )
        }
    }
}
