package com.example.newsapp.di


import android.content.Context
import com.example.newsapp.data.localstorage.LocalStorage
import com.example.newsapp.data.network.api.ApiService
import com.example.newsapp.data.network.utils.NetworkHelper
import com.example.newsapp.data.network.utils.ResponseConverter
import com.example.newsapp.data.repositoryImpl.RepositoryImpl
import com.example.newsapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * Dependency injection class that provides instances related to repositories
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideResponseConverter(): ResponseConverter {
        return ResponseConverter()
    }

    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        responseConverter: ResponseConverter,
        dispatcher: CoroutineDispatcher,
        networkHelper: NetworkHelper,
        authLocalStorage: LocalStorage
    ): Repository {
        return RepositoryImpl(apiService, responseConverter, dispatcher, networkHelper, authLocalStorage)
    }


    @Provides
    @Singleton
    fun provideLocalStorage(
        @ApplicationContext context: Context
    ): LocalStorage {
        return LocalStorage(context)
    }

}
