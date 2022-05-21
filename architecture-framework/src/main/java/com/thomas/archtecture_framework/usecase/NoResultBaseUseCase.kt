package com.thomas.archtecture_framework.usecase

abstract class NoResultBaseUseCase<PARAMS> : UseCase<Unit, PARAMS> {
    override suspend fun runAsync() {
        throw InvalidUseCaseCall("If you want to run without params and asynchronously, you should extend NoParamsBaseAsyncUseCase")
    }

    override fun runSync() {
        throw InvalidUseCaseCall("If you want to run without params and synchronously, you should extend NoParamsBaseUseCase")
    }

    override suspend fun runAsync(params: PARAMS) {
        throw InvalidUseCaseCall("If you want to run with params and asynchronously, you should extend BaseAsyncUseCase")
    }
}
