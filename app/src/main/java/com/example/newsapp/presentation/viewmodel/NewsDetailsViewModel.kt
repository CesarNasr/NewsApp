package com.example.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class NewsDetailsViewModel @Inject constructor() : ViewModel() {
    private val _articleData = MutableStateFlow<Article?>(null)
    val articleData: StateFlow<Article?> = _articleData


    fun setArticleData(article : Article){
        _articleData.value = article
    }
}