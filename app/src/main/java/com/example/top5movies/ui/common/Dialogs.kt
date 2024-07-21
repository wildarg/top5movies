package com.example.top5movies.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun TextFieldDialog(
    title: String,
    value: String = "",
    label: String? = null,
    onDismiss: () -> Unit = {},
    onResult: (String) -> Unit = {}
) {
    var text by rememberSaveable { mutableStateOf(value) }

    AlertDialog(
        title = { RowTitle(title) },
        text = {
            MainTextField(
                value = text,
                label = label,
                onValueChange = { text = it }
            )
        },
        confirmButton = {
            MainButton(
                text = "OK",
                width = 100.dp,
                onClick = { onResult(text) }
            )
        },
        dismissButton = { SecondButton(text = "Cancel", width = 100.dp, onClick = onDismiss) },
        onDismissRequest = onDismiss,
    )
}


@Preview
@Composable
fun TextFieldDialogPreview() {
    Top5MoviesTheme {
        TextFieldDialog(title = "Lorem Ipsum")
    }
}
