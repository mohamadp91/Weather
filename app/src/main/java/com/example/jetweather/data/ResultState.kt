package com.example.jetweather.data

sealed class ResultState<out T : Any>() {
    class Success<out T : Any>(val data: T) : ResultState<T>()
    object Loading : ResultState<Nothing>()
    class Error(val exception: Exception) : ResultState<Nothing>()
}
