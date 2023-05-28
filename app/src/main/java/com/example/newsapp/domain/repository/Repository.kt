package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.response.ApiEntry
import com.example.newsapp.data.model.response.Article
import com.example.newsapp.data.network.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun fetchNews(forceRefresh: Boolean = false): Flow<Resource<ApiEntry>>
    suspend fun searchArticles(query: String): Flow<Resource<List<Article>>>

}