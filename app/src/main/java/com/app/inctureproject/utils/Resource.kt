package com.app.inctureproject.utils

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>( data: T? = null,throwable: Throwable) : Resource<T>(data, throwable)
}