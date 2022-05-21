package com.thomas.datalayer.base

import android.util.Log
import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.archtecture_framework.wrapper.ResultCode
import com.thomas.archtecture_framework.wrapper.ResultWrapper
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseDataSourceImpl {
    companion object {
        const val MESSAGE = "PLEASE SEE LOGCAT FOR DETAILS"

        inline fun <SUCCESS, reified ERROR> safeApiCall(executeApiAsync: () -> Response<SUCCESS>): ResultWrapper<SUCCESS, ErrorWrapper<ERROR>> {
            return try {
                val response = executeApiAsync.invoke()
                ApiResponseHandler.build(response)
            } catch (exception: Exception) {
                Log.e("BaseDataSourceImpl", exception.message, exception)

                val baseErrorData = ErrorWrapper<ERROR>(
                    technicalErrorMessage = "$MESSAGE: ${exception.message}"
                )

                val statusCode = makeStatusCode(exception)

                ResultWrapper(
                    error = baseErrorData,
                    resultCode = statusCode
                )
            }
        }

        fun makeStatusCode(exception: Exception) = when (exception) {
            is SocketTimeoutException -> ResultCode.HTTP_RETROFIT_SOCKET_TIMEOUT_EXCEPTION
            is UnknownHostException -> ResultCode.HTTP_RETROFIT_UNKNOWN_HOST_EXCEPTION
            is ConnectException -> ResultCode.HTTP_RETROFIT_CONNECT_EXCEPTION
            is NoRouteToHostException -> ResultCode.HTTP_RETROFIT_NO_ROUTE_TO_HOST_EXCEPTION
            is IOException -> ResultCode.HTTP_RETROFIT_IO_EXCEPTION
            else -> ResultCode.HTTP_RETROFIT_DEFAULT_EXCEPTION
        }
    }
}
