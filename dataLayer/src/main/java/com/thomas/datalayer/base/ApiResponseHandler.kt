package com.thomas.datalayer.base

import com.google.gson.Gson
import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.archtecture_framework.wrapper.ResultCode
import com.thomas.archtecture_framework.wrapper.ResultWrapper
import com.thomas.datalayer.extensions.fromJsonGeneric
import okhttp3.Headers
import retrofit2.Response

object ApiResponseHandler {
    inline fun <SUCCESS, reified ERROR> build(response: Response<SUCCESS>): ResultWrapper<SUCCESS, ErrorWrapper<ERROR>> {
        val headers = getHeaders(response.headers())

        return if (response.isSuccessful) {
            makeBody(response, headers)
        } else {
            makeError(response, headers)
        }
    }

    fun getHeaders(headers: Headers): () -> MutableMap<String, String> {
        return {
            val keyValueMap: MutableMap<String, String> = HashMap()

            headers.names().map { headerKey ->
                val headerValue = headers.get(headerKey)
                keyValueMap[headerKey] = headerValue ?: ""
            }

            keyValueMap
        }
    }

    inline fun <reified ERROR, SUCCESS> makeBody(
            response: Response<SUCCESS>,
            headers: () -> MutableMap<String, String>
    ): ResultWrapper<SUCCESS, ErrorWrapper<ERROR>> {
        val body = response.body()
        return if (body != null)
            ResultWrapper(
                    success = body,
                    keyValueMap = headers(),
                    resultCode = response.code()
            )
        else
            ResultWrapper(
                    keyValueMap = headers(),
                    resultCode = ResultCode.APP_GENERIC_ERROR
            )
    }

    inline fun <reified ERROR, SUCCESS> makeError(
            response: Response<SUCCESS>,
            headers: () -> MutableMap<String, String>
    ): ResultWrapper<SUCCESS, ErrorWrapper<ERROR>> {
        var errorData: ERROR? = null

        if (ERROR::class != Unit::class) {
            val msg = response.errorBody()?.string()

            if (!msg.isNullOrEmpty()) {
                errorData = Gson().fromJsonGeneric<ERROR>(msg)
            }
        }

        return ResultWrapper(
                error = ErrorWrapper(
                        errorData,
                        response.message()
                ),
                keyValueMap = headers(),
                resultCode = response.code()
        )
    }
}
