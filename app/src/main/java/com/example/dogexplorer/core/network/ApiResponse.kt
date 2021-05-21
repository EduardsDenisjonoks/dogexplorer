package com.example.dogexplorer.core.network

sealed class ApiResponse<out T> {

    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val appError: Throwable) : ApiResponse<Nothing>()

    companion object {
        fun <T> success(data: T): ApiResponse<T> = Success(data)
        fun error(throwable: Throwable): ApiResponse<Nothing> = Error(throwable)
    }

}