package com.example.newsapp.data.repositoryImpl

import com.example.newsapp.data.localdb.database.AppDatabase
import com.example.newsapp.data.localstorage.LocalStorage
import com.example.newsapp.data.model.response.ApiEntry
import com.example.newsapp.data.model.response.Article
import com.example.newsapp.data.network.api.ApiService
import com.example.newsapp.data.network.utils.Resource
import com.example.newsapp.data.network.utils.ResponseConverter
import com.example.newsapp.data.utils.ArticleMapper
import com.example.newsapp.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
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
    private val db: AppDatabase,
    private val articleMapper: ArticleMapper,
    private val localStorage: LocalStorage
) : Repository {

    override suspend fun fetchNews(forceRefresh: Boolean): Flow<Resource<ApiEntry>> {

        // if user force refreshes the list, or the data requires update, fetch data from remote data source
        if (forceRefresh || shouldUpdateDB()) {
            fetchRemoteArticles()
        }

        // reactively listen to local db updates and emit data to the next layer
        return db.ArticleDao().getAll().transform {
            if (it.isEmpty()) {
                // if local list is empty, retry fetch data from remote data source
                emit(fetchRemoteArticles())
            } else {
                emit(Resource.Success(ApiEntry(articles = articleMapper.mapFromEntityList(it))))
            }
        }.flowOn(ioDispatcher)
    }


    override suspend fun searchArticles(query: String): Flow<Resource<List<Article>>> {
        return db.ArticleDao().searchArticles(query).transform {
            emit(Resource.Success(articleMapper.mapFromEntityList(it)))
        }.flowOn(ioDispatcher)
    }


    private suspend fun fetchRemoteArticles(): Resource<ApiEntry> {
        return withContext(ioDispatcher) {
            val results = responseConverter.responseToResults(apiService.fetchTopNews())
            updateCurrentTimeStamp(results)
            saveLocalNews(results)
            results
        }
    }


    // saves updated news to local db if results were success - API call successfully fetched
    private suspend fun saveLocalNews(result: Resource<ApiEntry>) = withContext(ioDispatcher) {
        if (result is Resource.Success<ApiEntry>) {
            result.data?.articles?.let {
                db.ArticleDao().insertAllArticles(articleMapper.mapToEntityList(it))
            }
        }
    }

    // updates timestamp if results were success - API call successfully fetched
    private fun updateCurrentTimeStamp(results: Resource<ApiEntry>) {
        if (results is Resource.Success<ApiEntry>) {
            saveTimeStamp(System.currentTimeMillis())
        }
    }

    private fun saveTimeStamp(timestamp: Long) {
        localStorage.saveTimeStampData(timestamp)
    }

    private fun getPrevTimeStamp(): Long = localStorage.getTimeStampData()

    // Checks weather 24 hrs has passed since the last remote api call occurred
    private fun shouldUpdateDB(): Boolean {
        return if (getPrevTimeStamp() <= -1) {
            true
        } else {
            System.currentTimeMillis() - getPrevTimeStamp() > (UPDATE_LOCALE_NEWS * 60 * 60 * 1000)
        }
    }
}