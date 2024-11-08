package com.example.top5movies.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun MainButton(
    text: String,
    width: Dp? = null,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier.let {
            if (width != null) it.width(width) else it.fillMaxWidth()
        },
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Composable
fun SecondButton(
    text: String,
    width: Dp? = null,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = Modifier.let {
            if (width != null) it.width(width) else it.fillMaxWidth()
        },
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun MainButtonPreview() {
    Top5MoviesTheme {
        MainButton("Hello")
    }
}

@Preview
@Composable
private fun SecondButtonPreview() {
    Top5MoviesTheme {
        SecondButton("Hello")
    }
}

