package com.example.newsapp.di

import com.example.newsapp.domain.repository.Repository
import com.example.newsapp.domain.usecases.FetchTopNewsUseCase
import com.example.newsapp.domain.usecases.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Dependency injection object that provides instances of use-cases
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchTopNewsUseCase(repository: Repository) = FetchTopNewsUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(repository: Repository) = SearchNewsUseCase(repository)

}