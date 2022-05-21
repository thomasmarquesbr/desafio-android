package com.thomas.archtecture_framework.usecase

abstract class NoParamsNoResultBaseUseCase: UseCase<Unit, NoParams> {

    override suspend fun runAsync() {
        throw InvalidUseCaseCall("If you want to run without params and asynchronously, you should extend NoParamsBaseAsyncUseCase")
    }

    override suspend fun runAsync(params: NoParams) {
        throw InvalidUseCaseCall("If you want to run with params and asynchronously, you should extend BaseAsyncUseCase")
    }

    override fun runSync(params: NoParams) {
        throw InvalidUseCaseCall("If you want to run with params and synchronously, you should extend BaseUseCase")
    }

}
