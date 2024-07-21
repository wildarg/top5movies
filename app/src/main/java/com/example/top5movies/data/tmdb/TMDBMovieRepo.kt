package com.example.top5movies.data.tmdb

import com.example.top5movies.data.tmdb.api.TMDBApi
import com.example.top5movies.data.tmdb.models.MovieResponse
import com.example.top5movies.domain.Movie
import com.example.top5movies.domain.repo.MovieRepo
import javax.inject.Inject

class TMDBMovieRepo @Inject constructor(
    private val api: TMDBApi
) : MovieRepo {

    override suspend fun searchMovies(filter: String): List<Movie> {
        return runCatching {
            api.searchMovies(filter)
        }.getOrNull()?.results
            ?.map(this::toMovies)
            ?: emptyList()
    }

    private fun toMovies(response: MovieResponse): Movie {
        return Movie(
            id = response.id,
            title = response.title,
            description = response.overview,
            posterUrl = "$POSTER_ORIGINAL_URL${response.posterPath}",
            posterPreviewUrl = "$POSTER_PREVIEW_URL${response.posterPath}",
        )
    }
}

private const val POSTER_ORIGINAL_URL = "https://image.tmdb.org/t/p/original"
private const val POSTER_PREVIEW_URL = "https://image.tmdb.org/t/p/w200"