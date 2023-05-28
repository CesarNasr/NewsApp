package com.example.newsapp.data.network.utils

import com.example.newsapp.data.model.response.ApiEntry
import com.example.newsapp.data.model.response.Article
import retrofit2.Response

/**
 * Responsible for converting api response to resource class
 */
class ResponseConverter {

    fun responseToResults(response: Response<ApiEntry>): Resource<ApiEntry> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        response.errorBody()?.let {
            return Resource.Error(
                response.code(),
                response.message()
            )
        }
        return Resource.Error(
            response.code(),
            response.message()
        )
    }

}