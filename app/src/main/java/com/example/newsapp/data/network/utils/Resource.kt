package com.example.newsapp.data.network.utils
/**
 * Sealed Class to monitor the API calls state and update the UI, can hold dynamic data and messages
 */
sealed class Resource<T>(
    val data: T? = null,
    val errorCode : Int? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(errorCode : Int, message: String, data: T? = null) : Resource<T>(data,errorCode, message)
}
