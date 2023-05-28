package com.example.newsapp.data.network.api

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.response.ApiEntry
import com.example.newsapp.data.network.utils.ApiConstants.COUNTRY
import retrofit2.Response
import retrofit2.http.*


/**
 * Api Service class calling @GET , @POST,  @DELETE, @UPDATE, @EDIT etc ...
 */
interface ApiService {
    @GET("top-headlines?country=${COUNTRY}&apiKey=${BuildConfig.API_KEY}")
    suspend fun fetchTopNews(): Response<ApiEntry>
}
