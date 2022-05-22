package com.picpay.desafio.android.extensions

import com.thomas.archtecture_framework.state.ViewState
import com.thomas.archtecture_framework.wrapper.ResultWrapper

/**
 * Retorna um ViewState de acordo com o resultado do ResultWrapper passado
 *
 * @param SUCCESS modelo de sucesso
 */
fun <SUCCESS, ERROR> loadViewState(resultWrapper: ResultWrapper<SUCCESS, ERROR>): ViewState<SUCCESS, ERROR> {
    return if (resultWrapper.isSuccess()) {
        handleSuccessfulViewState(resultWrapper.success!!)
    } else {
        ViewState.Error(error = resultWrapper.error)
    }
}

/**
 * Retorna estados de sucesso, incluindo o caso de modelo de sucesso ser uma lista
 */
private fun <SUCCESS, ERROR> handleSuccessfulViewState(data: SUCCESS): ViewState<SUCCESS, ERROR> {
    return if (data is List<*> && data.isNullOrEmpty()) {
        ViewState.Empty()
    } else {
        ViewState.Success(data)
    }
}
