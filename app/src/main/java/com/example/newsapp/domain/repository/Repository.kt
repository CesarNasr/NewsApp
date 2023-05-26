package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.response.ApiEntry
import com.example.newsapp.data.model.response.Article
import com.example.newsapp.data.network.utils.Resource

interface Repository {

    // api calls
    suspend fun fetchArticles(): Resource<ApiEntry>
    suspend fun searchArticles(query: String): Resource<ApiEntry>

    // local
    fun saveLocalNews(articles: List<Article>)
    fun fetchLocalNews(): List<Article>
}