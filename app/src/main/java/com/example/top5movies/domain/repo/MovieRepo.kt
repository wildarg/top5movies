package com.example.top5movies.domain.repo

import com.example.top5movies.domain.Movie

interface MovieRepo {
    suspend fun searchMovies(filter: String): List<Movie>
}