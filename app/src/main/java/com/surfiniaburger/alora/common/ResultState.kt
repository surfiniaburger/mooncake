package com.surfiniaburger.alora.common

sealed class ResultState<out T> {
    object Loading : ResultState<Nothing>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val type: ErrorType, val message: String? = null) : ResultState<Nothing>()
}

enum class ErrorType {
    NOT_FOUND,
    SERVER_ERROR,
    NETWORK_ERROR,
    GENERIC
}
