package com.example.newsapp.domain.repository

import com.example.newsapp.data.localdb.model.LocalArticle
import com.example.newsapp.data.model.response.ApiEntry
import com.example.newsapp.data.network.utils.Resource

interface Repository {

    /*remote api calls*/
    suspend fun fetchArticles(): Resource<ApiEntry>
    suspend fun searchArticles(query: String): Resource<ApiEntry>

    /*local db*/
    fun saveLocalNews(articles: List<LocalArticle>)
    fun fetchLocalNews(): List<LocalArticle>

    /*local storage*/
    fun saveTimeStamp(timestamp : Long)
    fun getTimeStamp(): Long
}