package com.example.top5movies.ui.search.user.widget

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.domain.Movie
import com.example.top5movies.domain.Top5
import com.example.top5movies.ui.common.RowTitle
import com.example.top5movies.ui.common.theme.Top5MoviesTheme
import com.example.top5movies.ui.home.widget.MovieItem

@Composable
fun Top5SwimLane(
    top5: Top5
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RowTitle(title = top5.title)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(top5.movies) { movie ->
                MovieItem(movie)
            }
        }
        if (top5.movies.isEmpty())
            Text(
                text = "No movies in the list",
                style = MaterialTheme.typography.labelMedium
                    .copy(color = MaterialTheme.colorScheme.tertiary)
            )
    }
}

@Preview
@Composable
private fun Top5SwimLanePreview() {
    Top5MoviesTheme {
        Top5SwimLane(
            top5 = Top5(
                title = "Lorem Ipsum",
                movies = listOf(
                    Movie(
                        posterUrl = ""
                    ),
                    Movie(
                        posterUrl = ""
                    )
                )
            )
        )
    }
}

@Preview
@Composable
private fun Top5SwimLaneEmptyPreview() {
    Top5MoviesTheme {
        Top5SwimLane(
            top5 = Top5(
                title = "Lorem Ipsum",
                movies = emptyList()
            )
        )
    }
}