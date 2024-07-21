package com.example.top5movies.ui.home.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.top5movies.domain.Movie
import com.example.top5movies.ui.common.theme.Top5MoviesTheme


@Composable
fun MovieItem(
    movie: Movie,
    onClick: () -> Unit = {}
) {
    AsyncImage(
        modifier = Modifier
            .width(100.dp)
            .aspectRatio(1f / 1.5f)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceBright)
            .clickable(onClick = onClick),
        contentScale = ContentScale.Fit,
        model = movie.posterPreviewUrl,
        contentDescription = ""
    )
}

@Composable
fun NewMovieButton(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .aspectRatio(1f / 1.5f)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceBright)
            .clickable(onClick = onClick),
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            imageVector = Icons.Rounded.Add,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    Top5MoviesTheme {
        MovieItem(movie = Movie(posterUrl = ""))
    }
}

@Preview
@Composable
fun NewMovieButtonPreview() {
    Top5MoviesTheme {
        NewMovieButton()
    }
}