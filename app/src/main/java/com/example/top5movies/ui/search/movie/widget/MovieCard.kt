package com.example.top5movies.ui.search.movie.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.top5movies.R
import com.example.top5movies.domain.Movie
import com.example.top5movies.ui.common.theme.Top5MoviesTheme
import com.example.top5movies.ui.common.theme.Typography

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(1f / 1.5f)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceBright),
            contentScale = ContentScale.Fit,
            model = movie.posterPreviewUrl,
            contentDescription = "",
            error = painterResource(id = R.drawable.baseline_error_outline_24)
        )
        Column(
            modifier = Modifier.weight(1.0f)
        ) {
            Text(
                text = movie.title,
                style = Typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = movie.description,
                style = Typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Preview
@Composable
fun MovieCardPreview() {
    Top5MoviesTheme {
        MovieCard(
            movie = Movie(
                id = 0,
                title = "Liar Liar",
                description = "Lorem ipsum dolor sit amet",
                posterUrl = ""
            )
        )
    }
}