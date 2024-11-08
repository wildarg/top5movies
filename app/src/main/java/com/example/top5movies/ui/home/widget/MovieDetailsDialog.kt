package com.example.top5movies.ui.home.widget

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.domain.Movie
import com.example.top5movies.ui.common.MainButton
import com.example.top5movies.ui.common.SecondButton
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun MovieDetailsDialog(
    movie: Movie,
    onDismissRequest: () -> Unit = {},
    onDeleteRequest: () -> Unit = {}
) {
    AlertDialog(
        title = { Text(movie.title) },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            MainButton(
                text = "Delete from list",
                onClick = onDeleteRequest
            )
        },
        dismissButton = {
            SecondButton(text = "Cancel", onClick = onDismissRequest)
        },
        text = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MovieItem(movie)
                Text(movie.description)
            }
        }
    )
}

@Preview
@Composable
fun MovieDetailsDialogPreview() {
    Top5MoviesTheme {
        MovieDetailsDialog(
            Movie(
                title = "Lorem Ipsum",
                description = "Lorem Ipsum Dolor Sit Amet",
                posterUrl = ""
            )
        )
    }
}