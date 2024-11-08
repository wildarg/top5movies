package com.example.top5movies.ui.search.user.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.top5movies.domain.Account
import com.example.top5movies.domain.Top5
import com.example.top5movies.ui.common.MainButton
import com.example.top5movies.ui.common.PageTitle
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun AccountFeedDialog(
    account: Account,
    feed: List<Top5>,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                PageTitle(title = account.email)
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(feed) { top5 ->
                        Top5SwimLane(top5)
                    }
                }
                MainButton(text = "Close", onClick = onDismissRequest)
            }
        }
    }
}

@Preview
@Composable
private fun AccountFeedDialogPreview() {
    Top5MoviesTheme {
        AccountFeedDialog(
            account = Account("lorem@ipsum.com"),
            feed = listOf(
                Top5("Lorem ipsum")
            )
        )
    }
}