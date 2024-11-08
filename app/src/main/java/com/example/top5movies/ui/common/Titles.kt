package com.example.top5movies.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.top5movies.ui.common.theme.Top5MoviesTheme
import com.example.top5movies.ui.common.theme.Typography

@Composable
fun PageTitle(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = title,
        style = Typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onSurface)
    )
}

@Composable
fun RowTitle(
    title: String,
    menuItems: @Composable (ColumnScope.() -> Unit)? = null
) {
    val isMenuExpanded = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = Typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurface)
        )
        if (menuItems != null)
            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = { isMenuExpanded.value = true }) {
                    Icon(Icons.Rounded.MoreVert, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
                }
                DropdownMenu(
                    expanded = isMenuExpanded.value,
                    onDismissRequest = { isMenuExpanded.value = false },
                    content = menuItems
                )
            }
    }
}

@Preview
@Composable
fun RowTitlePreview() {
    Top5MoviesTheme {
        RowTitle("Lorem Ipsum")
    }
}