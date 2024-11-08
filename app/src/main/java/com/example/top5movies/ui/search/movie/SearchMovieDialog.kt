package com.example.top5movies.ui.search.movie

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.top5movies.domain.Movie
import com.example.top5movies.ui.search.movie.widget.SearchMovieLayout
import okhttp3.Request

@Composable
fun SearchMovieDialog(
    onSelectMovie: (Movie) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {

    val vm: SearchMovieViewModel = hiltViewModel()
    val movies by vm.state.collectAsState()

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(16.dp)
        ) {
            SearchMovieLayout(
                movies = movies,
                onFilterChange = vm::setFilter,
                onSelectMovie = { movie ->
                    onSelectMovie(movie)
                    onDismissRequest()
                }
            )
        }
    }
}