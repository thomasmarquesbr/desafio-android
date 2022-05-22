package com.thomas.domainlayer.features.users

import com.thomas.archtecture_framework.failure.FailureFactory
import com.thomas.archtecture_framework.usecase.NoParamsBaseAsyncUseCase
import com.thomas.archtecture_framework.wrapper.BaseError
import com.thomas.archtecture_framework.wrapper.ResultWrapper
import com.thomas.domainlayer.base.ErrorTranslate
import com.thomas.domainlayer.base.FailureHandler
import com.thomas.domainlayer.features.users.model.UserModel

class GetContactsUseCase(
    private val usersRepository: UsersRepository
): NoParamsBaseAsyncUseCase<ResultWrapper<List<UserModel>, GetContactsFailureFactory<BaseError>>>() {

    override suspend fun runAsync(): ResultWrapper<List<UserModel>, GetContactsFailureFactory<BaseError>> {
        return usersRepository.getUsers()
            .transformError(FailureHandler(GetContactsFailureFactory()).transform())
    }
}

open class GetContactsFailureFactory<ERROR_DATA> : FailureFactory<ERROR_DATA>() {
    class BaseFailure<ERROR_DATA>(override var errorData: ERROR_DATA? = null) : GetContactsFailureFactory<ERROR_DATA>()
    class GenericFailure<ERROR_DATA> : GetContactsFailureFactory<ERROR_DATA>()

    // Demonstração de mapeamento de erros específicos
    class CustomError1Failure<ERROR_DATA> : GetContactsFailureFactory<ERROR_DATA>()
    class CustomError2Failure<ERROR_DATA> : GetContactsFailureFactory<ERROR_DATA>()
    class CustomError3Failure<ERROR_DATA> : GetContactsFailureFactory<ERROR_DATA>()

    override fun getFailure(errorTranslate: Int): GetContactsFailureFactory<ERROR_DATA> {
        return when (errorTranslate) {
            ErrorTranslate.BASE_FAILURE.code -> BaseFailure()
            ErrorTranslate.CUSTOM_ERROR_1.code -> CustomError1Failure()
            ErrorTranslate.CUSTOM_ERROR_2.code -> CustomError2Failure()
            ErrorTranslate.CUSTOM_ERROR_3.code -> CustomError3Failure()
            else -> GenericFailure()
        }
    }
}
