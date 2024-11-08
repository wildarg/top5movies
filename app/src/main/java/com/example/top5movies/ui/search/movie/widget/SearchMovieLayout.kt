package com.example.top5movies.ui.search.movie.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.domain.Movie
import com.example.top5movies.ui.common.MainTextField
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun SearchMovieLayout(
    movies: List<Movie> = emptyList(),
    onFilterChange: (String) -> Unit = {},
    onSelectMovie: (Movie) -> Unit = {}
) {
    var filter by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MainTextField(
            leadingIcon = Icons.Rounded.Search,
            value = filter,
            onValueChange = { text ->
                filter = text
                onFilterChange(text)
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(movies) { movie ->
                MovieCard(movie) { onSelectMovie(movie) }
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}

@Preview
@Composable
fun SearchMovieLayoutPreview() {
    Top5MoviesTheme {
        SearchMovieLayout()
    }
}