package com.example.newsapp.data.repositoryImpl

import com.example.newsapp.data.localdb.database.AppDatabase
import com.example.newsapp.data.localdb.model.LocalArticle
import com.example.newsapp.data.localstorage.LocalStorage
import com.example.newsapp.data.model.response.ApiEntry
import com.example.newsapp.data.model.response.Article
import com.example.newsapp.data.network.api.ApiService
import com.example.newsapp.data.network.utils.NetworkHelper
import com.example.newsapp.data.network.utils.Resource
import com.example.newsapp.data.network.utils.ResponseConverter
import com.example.newsapp.data.utils.ArticleMapper
import com.example.newsapp.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Actual implementation of the repository that communicates with remote source and to local database
 *  Repository acts as a single source of truth for data in our app
 */

const val UPDATE_LOCALE_NEWS = 24 //in hrs

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val responseConverter: ResponseConverter,
    private val ioDispatcher: CoroutineDispatcher,
    private val networkHelper: NetworkHelper,
    private val db: AppDatabase,
    private val articleMapper: ArticleMapper,
    private val localStorage: LocalStorage
) : Repository {

    override suspend fun fetchArticles(): Resource<ApiEntry> {
        if (shouldUpdateDB()) {
            saveTimeStamp(currentTimeStamp)
            saveLocalNews(articleMapper.mapToEntityList(news))
        }


        return withContext(ioDispatcher) {
            responseConverter.responseToResults(apiService.fetchTopNews())

        }
    }

    override suspend fun searchArticles(query: String): Resource<ApiEntry> {
        return withContext(ioDispatcher) {
            responseConverter.responseToResults(apiService.searchNews(query))
        }
    }

    override fun saveLocalNews(articles: List<LocalArticle>) {
        db.ArticleDao().insertAll(articles)
    }

    override fun fetchLocalNews(): List<LocalArticle> = db.ArticleDao().getAll()


    override fun saveTimeStamp(timestamp: Long) {
        localStorage.saveTimeStampData(timestamp)
    }

    override fun getTimeStamp(): Long = localStorage.getTimeStampData()



    private fun shouldUpdateDB(): Boolean {
        val currentTimeStamp = System.currentTimeMillis()
        return currentTimeStamp - getTimeStamp() > (UPDATE_LOCALE_NEWS * 60 * 60 * 1000)
    }
}