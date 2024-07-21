package com.example.top5movies.di

import com.example.top5movies.data.tmdb.api.AuthInterceptor
import com.example.top5movies.data.tmdb.api.TMDBApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.top5movies.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(TMDB_TOKEN))
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    fun provideTMDBApi(client: OkHttpClient): TMDBApi {
        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(TMDBApi::class.java)
    }

}

private const val TMDB_BASE_URL = "https://api.themoviedb.org"
private const val TMDB_TOKEN = BuildConfig.TMDB_ACCESS_TOKEN