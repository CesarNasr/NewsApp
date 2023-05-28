package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.localdb.database.AppDatabase
import com.example.newsapp.data.localdb.database.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency injection class that provides instances related to local room db
 */
@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideRoomInstance(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.invoke(context)
    }
}