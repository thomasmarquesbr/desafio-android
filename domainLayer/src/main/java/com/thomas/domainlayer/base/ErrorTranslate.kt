package com.thomas.domainlayer.base

enum class ErrorTranslate(val code: Int) {
    GENERIC_FAILURE(code = -1),
    BASE_FAILURE(code = 0),
    CUSTOM_ERROR_1(code = 1001),
    CUSTOM_ERROR_2(code = 1002),
    CUSTOM_ERROR_3(code = 1003)
}
