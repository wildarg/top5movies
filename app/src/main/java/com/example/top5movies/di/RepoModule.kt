package com.example.top5movies.di

import com.example.top5movies.data.firebase.FirebaseAccountRepo
import com.example.top5movies.data.firebase.FirestoreTop5FeedRepo
import com.example.top5movies.data.firebase.Top5Mapper
import com.example.top5movies.data.firebase.Top5MapperImpl
import com.example.top5movies.data.tmdb.TMDBMovieRepo
import com.example.top5movies.domain.repo.AccountRepo
import com.example.top5movies.domain.repo.MovieRepo
import com.example.top5movies.domain.repo.Top5FeedRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Singleton
    @Binds
    fun bindAccountRepo(impl: FirebaseAccountRepo): AccountRepo

    @Singleton
    @Binds
    fun bindFeedRepo(impl: FirestoreTop5FeedRepo): Top5FeedRepo

    @Binds
    fun bindTop5Mapper(impl: Top5MapperImpl): Top5Mapper

    @Binds
    fun bindMovieRepo(impl: TMDBMovieRepo): MovieRepo
}