package com.senemyalin.cocktailsapp.common

import java.lang.Exception

sealed class NetworkResponse<out T: Any> {
    object Loading: NetworkResponse<Nothing>()
    data class Error(val exception: Exception): NetworkResponse<Nothing>()
    data class Success<out T: Any>(val result: T?): NetworkResponse<T>()
}