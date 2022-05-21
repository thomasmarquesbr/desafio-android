package com.thomas.archtecture_framework.wrapper

class ResultCode {

    companion object {
        // SUCCESS
        const val HTTP_SUCCESS_OK = 200
        const val HTTP_SUCCESS_CREATED = 201

        // REDIRECTIONS
        const val HTTP_REDIRECTION_MULTIPLE_OPTIONS = 300

        // CLIENT ERROR
        const val HTTP_CLIENT_BAD_REQUEST = 400
        const val HTTP_CLIENT_UNAUTHORIZED = 401
        const val HTTP_CLIENT_FORBIDDEN = 403
        const val HTTP_CLIENT_NOT_FOUND = 404
        const val HTTP_UN_PROCESSABLE_ENTITY = 422

        // SERVER ERROR
        const val HTTP_SERVER_INTERNAL_ERROR = 500
        const val HTTP_SERVER_SERVICE_UNAVAILABLE = 503

        // SERVER ERROR
        const val HTTP_RETROFIT_DEFAULT_EXCEPTION = 1500
        const val HTTP_RETROFIT_SOCKET_TIMEOUT_EXCEPTION = 1501
        const val HTTP_RETROFIT_UNKNOWN_HOST_EXCEPTION = 1502
        const val HTTP_RETROFIT_CONNECT_EXCEPTION = 1503
        const val HTTP_RETROFIT_NO_ROUTE_TO_HOST_EXCEPTION = 1503
        const val HTTP_RETROFIT_IO_EXCEPTION = 1504
        const val HTTP_RETROFIT_NULL_BODY_EXCEPTION = 1505

        // APP ERROR
        const val APP_GENERIC_ERROR = 2500
    }
}
