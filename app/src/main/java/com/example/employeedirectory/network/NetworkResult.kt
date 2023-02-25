package com.example.employeedirectory.network

/**
 * class taken from https://www.howtodoandroid.com/android-app-using-mvvm-coroutines-flow-hilt/ to
 * emit different statuses of the network call
 * */
sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure<T>(val errorMessage: String) : NetworkResult<T>()
}