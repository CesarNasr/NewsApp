package com.example.newsapp.presentation.utils

import com.example.newsapp.data.model.response.Article


sealed class UiState {
    /*object Empty : UiState()
    object Loading : UiState()*/
    class Loaded(val data: List<Article>? = null, ) : UiState()

    class Error(val message: String? = null) : UiState()
}