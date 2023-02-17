package com.tasha.archimain.data

data class ApiResult<out T>(val status: Status, val data: T?, val errorType: ErrorType?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null)
        }

        fun <T> error(errorType: ErrorType? = null, data: T? = null): ApiResult<T> {
            return ApiResult(Status.ERROR, data, errorType)
        }

        fun <T> loading(data: T? = null): ApiResult<T> {
            return ApiResult(Status.LOADING, data, null)
        }
    }
}