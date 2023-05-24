package com.example.newsapp.data.network.utils

import com.example.newsapp.data.model.response.auth.Authorization
import com.example.newsapp.data.model.response.trips.TripSearch
import retrofit2.Response

/**
 * Responsible for converting api response to resource class
 */
class ResponseConverter {

    fun searchTripsResponseToResult(response: Response<TripSearch>): Resource<TripSearch> {
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

    fun authResponseToResult(response: Response<Authorization>): Resource<Authorization> {
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