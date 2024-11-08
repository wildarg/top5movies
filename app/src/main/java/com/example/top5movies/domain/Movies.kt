package com.example.top5movies.domain

private const val MAX_MOVIES: Int = 5

data class Movie(
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val posterUrl: String,
    val posterPreviewUrl: String = posterUrl
)

data class Top5(
    val title: String,
    val movies: List<Movie> = emptyList()
) {
    val isComplete: Boolean
        get() = movies.size >= MAX_MOVIES

    fun hasMovie(movie: Movie): Boolean {
        return movies.any { it.id == movie.id }
    }
}