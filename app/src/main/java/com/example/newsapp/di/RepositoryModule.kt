package com.example.newsapp.di


import android.content.Context
import com.example.newsapp.data.localstorage.AuthLocalStorage
import com.example.newsapp.data.network.utils.ResponseConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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

  /*  @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        responseConverter: ResponseConverter,
        dispatcher: CoroutineDispatcher,
        networkHelper: NetworkHelper,
        authLocalStorage: AuthLocalStorage
    ): Repository {
        return RepositoryImpl(apiService, responseConverter, dispatcher, networkHelper, authLocalStorage)
    }
*/

    @Provides
    @Singleton
    fun provideAuthLocalStorage(
        @ApplicationContext context: Context
    ): AuthLocalStorage {
        return AuthLocalStorage(context)
    }

}
