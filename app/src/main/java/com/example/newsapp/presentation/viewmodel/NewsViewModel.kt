package com.example.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.R
import com.example.newsapp.data.network.utils.Resource
import com.example.newsapp.domain.repository.Repository
import com.example.newsapp.presentation.utils.ResourcesProvider
import com.example.newsapp.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _showLoadingView = MutableStateFlow(true)
    val showLoadingView: StateFlow<Boolean> = _showLoadingView

    private val _newsUiState = MutableSharedFlow<UiState>()
    val newsUiState: SharedFlow<UiState> = _newsUiState

    init {
        fetchNews()
    }


    fun fetchNews(forceRefresh: Boolean = false) {
        toggleLoadingView(!forceRefresh)

        viewModelScope.launch {
            try {
                repository.fetchNews(forceRefresh).collect {

                    when (it) {
                        is Resource.Success -> {
                            _newsUiState.emit(UiState.Loaded(data = it.data?.articles))
                        }

                        is Resource.Error -> {
                            onErrorOccurred(false)
                        }

                        is Resource.Loading -> {}
                    }
                    toggleLoadingView(false)

                }
            } catch (e: Exception) {
                toggleLoadingView(false)
                onErrorOccurred(e is HttpException || e is IOException)
            }
        }
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            try {
                repository.searchArticles(query).collect {

                    when (it) {
                        is Resource.Success -> {
                            _newsUiState.emit(UiState.Loaded(data = it.data))
                        }

                        is Resource.Error -> {
                            onErrorOccurred(false)
                        }

                        is Resource.Loading -> {}
                    }
                    toggleLoadingView(false)

                }
            } catch (e: Exception) {
                toggleLoadingView(false)
                onErrorOccurred(e is HttpException || e is IOException)
            }
        }
    }

    private fun toggleLoadingView(showLoadingView: Boolean) {
        _showLoadingView.value = showLoadingView
    }


    private fun onErrorOccurred(isInternetError: Boolean) {
        var errorMessage = resourcesProvider.getString(R.string.generic_error)
        if (isInternetError) errorMessage =
            resourcesProvider.getString(R.string.no_internet_error)

        viewModelScope.launch {
            _newsUiState.emit(UiState.Error(message = errorMessage))
        }
    }


}


