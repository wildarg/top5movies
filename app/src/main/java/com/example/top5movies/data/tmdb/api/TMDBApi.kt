package com.example.top5movies.data.tmdb.api

import com.example.top5movies.data.tmdb.models.MovieResponse
import com.example.top5movies.data.tmdb.models.PagedResult
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("/3/search/movie")
    suspend fun searchMovies(@Query("query") query: String): PagedResult<MovieResponse>
}