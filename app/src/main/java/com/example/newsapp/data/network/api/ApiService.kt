package com.example.newsapp.data.network.api

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.response.ApiEntry
import retrofit2.Response
import retrofit2.http.*


/**
 * Api Service class calling @GET , @POST,  @DELETE, @UPDATE, @EDIT etc ...
 */
interface ApiService {

    //Note: Some hardcoded values such as country will not be hardcoded in a production version.

    @GET("top-headlines?country=us&apiKey=${BuildConfig.API_KEY}")
    suspend fun fetchTopNews(): Response<ApiEntry>

}
