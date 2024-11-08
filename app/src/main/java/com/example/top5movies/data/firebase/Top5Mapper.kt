package com.example.top5movies.data.firebase

import com.example.top5movies.domain.Movie
import com.example.top5movies.domain.Top5
import javax.inject.Inject

interface Top5Mapper {
    fun fromMap(map: Map<String, Any?>): List<Top5>
    fun toMap(top5: Top5): Map<String, Any>
    fun toMap(movies: List<Movie>): List<Map<String, Any>>
}

class Top5MapperImpl @Inject constructor() : Top5Mapper {

    @Suppress("UNCHECKED_CAST")
    override fun fromMap(map: Map<String, Any?>): List<Top5> {
        return map.entries.map { entry ->
            Top5(
                title = entry.key,
                movies = (entry.value as? List<Any?>).orEmpty()
                    .map { (it as Map<String, Any?>).toMovie() }
            )
        }
    }

    override fun toMap(top5: Top5): Map<String, Any> {
        return mapOf(
            top5.title to toMap(top5.movies)
        )
    }

    override fun toMap(movies: List<Movie>): List<Map<String, Any>> {
        return movies.map { it.toMap() }
    }

    private fun Movie.toMap(): Map<String, Any> {
        return mapOf(
            UID_KEY to id,
            TITLE_KEY to title,
            DESCRIPTION_KEY to description,
            POSTER_URL_KEY to posterUrl,
            POSTER_PREVIEW_URL_KEY to posterPreviewUrl
        )
    }

    private fun Map<String, Any?>.toMovie(): Movie {
        return Movie(
            id = (get(UID_KEY) as? Long) ?: 0,
            title = (get(TITLE_KEY) as? String?).orEmpty(),
            description = (get(DESCRIPTION_KEY) as? String?).orEmpty(),
            posterUrl = (get(POSTER_URL_KEY) as? String?).orEmpty(),
            posterPreviewUrl = (get(POSTER_PREVIEW_URL_KEY) as? String?).orEmpty(),
        )
    }

    companion object {
        private const val UID_KEY = "uid"
        private const val TITLE_KEY = "title"
        private const val DESCRIPTION_KEY = "description"
        private const val POSTER_URL_KEY = "poster_url"
        private const val POSTER_PREVIEW_URL_KEY = "poster_preview_url"
    }
}