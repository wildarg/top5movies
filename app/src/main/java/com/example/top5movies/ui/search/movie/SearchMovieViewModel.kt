package com.example.top5movies.ui.search.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top5movies.domain.Movie
import com.example.top5movies.domain.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@Suppress("OPT_IN_USAGE")
@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val repo: MovieRepo
): ViewModel() {

    private val filterState = MutableStateFlow("")
    private val _state = MutableStateFlow<List<Movie>>(emptyList())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            filterState.debounce(300.milliseconds)
                .collect(this@SearchMovieViewModel::searchMovies)
        }
    }

    fun setFilter(text: String) {
        viewModelScope.launch { filterState.emit(text) }
    }

    private suspend fun searchMovies(filter: String) {
        val result = repo.searchMovies(filter)
        _state.emit(result)
    }

}