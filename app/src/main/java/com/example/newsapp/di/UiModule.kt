package com.example.newsapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.newsapp.presentation.utils.ResourcesProvider

@Module
@InstallIn(SingletonComponent::class)
object UiModule {
/*
    @Provides
    @Singleton
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }*/

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context) : ResourcesProvider {
        return ResourcesProvider(context)
    }

}