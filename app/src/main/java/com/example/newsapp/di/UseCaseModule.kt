package com.example.newsapp.di

import com.example.newsapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Dependency injection object that provides instances of use-cases if existed
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

}