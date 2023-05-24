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


    //https://newsapi.org/v2/top-headlines?country=us&apiKey=c0f82999e4e04da68ccf37aa2c24a1ef
    @GET("/top-headlines?country=us&apiKey=${BuildConfig.API_KEY}")
    suspend fun fetchTopNews(): Response<ApiEntry>


    // GET https://newsapi.org/v2/everything?q=bitcoin&apiKey=c0f82999e4e04da68ccf37aa2c24a1ef
    @GET("/everything?q={query}&apiKey=${BuildConfig.API_KEY}")
    suspend fun searchNews(
        @Path("query") query: String,
    ): Response<ApiEntry>
}
