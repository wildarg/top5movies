package com.example.top5movies.ui.home.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.domain.Movie
import com.example.top5movies.domain.Top5
import com.example.top5movies.ui.common.RowTitle
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun Top5Row(
    top5: Top5,
    onDelete: () -> Unit = {},
    onRename: () -> Unit = {},
    onAddMovieClick: () -> Unit = {},
    onMovieClick: (Movie) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        RowTitle(title = top5.title) {
            DropdownMenuItem(text = { Text("Rename") }, onClick = onRename)
            DropdownMenuItem(text = { Text("Delete") }, onClick = onDelete)
        }
        LazyRow(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(top5.movies) { movie ->
                MovieItem(movie) { onMovieClick(movie) }
            }
            if (!top5.isComplete)
                item { NewMovieButton(onClick = onAddMovieClick) }
        }
    }
}

@Preview
@Composable
fun Top5RowPreview() {
    Top5MoviesTheme {
        Top5Row(
            Top5(
                title = "Lorem Ipsum",
                movies = emptyList()
            )
        )
    }
}