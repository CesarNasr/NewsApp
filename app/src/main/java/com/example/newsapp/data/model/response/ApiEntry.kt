package com.example.newsapp.data.model.response

data class ApiEntry(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)