package com.example.top5movies.ui.home.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.domain.Movie
import com.example.top5movies.domain.Top5
import com.example.top5movies.ui.common.PageTitle
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun HomeLayout(
    paddings: PaddingValues,
    feed: List<Top5> = emptyList(),
    onDelete: (Top5) -> Unit = {},
    onRename: (Top5) -> Unit = {},
    onAddMovie: (Top5) -> Unit = {},
    onMovieClick: (Top5, Movie) -> Unit = { _, _ -> }
) {
    Column(
        modifier = Modifier
            .padding(paddings)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PageTitle("My Top Movies")
        Spacer(modifier = Modifier.height(36.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(feed) { top5 ->
                Top5Row(
                    top5 = top5,
                    onDelete = { onDelete(top5) },
                    onRename = { onRename(top5) },
                    onAddMovieClick = { onAddMovie(top5) },
                    onMovieClick = { movie -> onMovieClick(top5, movie) }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeLayoutPreview() {
    Top5MoviesTheme {
        HomeLayout(
            PaddingValues(),
            feed = listOf(
                Top5("Hello", movies = listOf())
            )
        )
    }
}