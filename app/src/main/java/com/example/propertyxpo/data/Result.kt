package com.example.propertyxpo.data

import java.lang.Exception


sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T? = null) : Result<T>()
    data class Failed<out T>(val data: T? = null, val exception: Throwable? = null) : Result<T>()
}
