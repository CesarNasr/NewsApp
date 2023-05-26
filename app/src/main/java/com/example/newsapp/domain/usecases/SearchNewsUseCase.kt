package com.example.newsapp.domain.usecases

import com.example.newsapp.data.model.response.ApiEntry
import com.example.newsapp.data.network.utils.Resource
import com.example.newsapp.domain.repository.Repository
import javax.inject.Inject


class SearchNewsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(query: String): Resource<ApiEntry> {
        return repository.searchArticles(query)
    }
}