package com.thomas.archtecture_framework.state

sealed class ViewState<SUCCESS, ERROR> {
    class Success<SUCCESS, ERROR>(
        val success: SUCCESS
    ) : ViewState<SUCCESS, ERROR>()

    class Error<SUCCESS, ERROR>(
        val error: ERROR? = null
    ) : ViewState<SUCCESS, ERROR>()

    class Empty<SUCCESS, ERROR> : ViewState<SUCCESS, ERROR>()

    class Loading<SUCCESS, ERROR> : ViewState<SUCCESS, ERROR>()
}
