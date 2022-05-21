package com.thomas.archtecture_framework.failure

abstract class FailureFactory<ERROR_DATA>
    : Failure<ERROR_DATA>() {
    abstract fun getFailure(errorTranslate: Int): FailureFactory<ERROR_DATA>
}

abstract class Failure<ERROR_DATA>(
        open var errorData: ERROR_DATA? = null,
)
