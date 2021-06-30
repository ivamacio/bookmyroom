package com.core.network

enum class ErrorCode {
    SERVER_UNEXPECTED_ERROR,
    JSON_PARSE_EXCEPTION,
    APP_UNEXPECTED_ERROR,
    NO_INTERNET
}

sealed class ResponseResult<out T> {

    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error(val errorCode: ErrorCode) : ResponseResult<Nothing>()

}