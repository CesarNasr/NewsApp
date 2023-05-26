package com.example.newsapp.data.model.response

import java.io.Serializable

data class ApiEntry(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Serializable