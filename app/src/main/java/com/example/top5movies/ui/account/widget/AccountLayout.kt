package com.example.top5movies.ui.account.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.domain.Account
import com.example.top5movies.ui.common.MainButton
import com.example.top5movies.ui.common.PageTitle
import com.example.top5movies.ui.common.RowTitle
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun AccountLayout(
    paddings: PaddingValues = PaddingValues(8.dp),
    account: Account?,
    onLogoutClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(paddings)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PageTitle(title = "Account")
        RowTitle(title = account?.email.orEmpty())
        Spacer(modifier = Modifier.height(100.dp))
        MainButton(text = "Log Out", onClick = onLogoutClick)
    }
}

@Preview
@Composable
private fun AccountLayoutPreview() {
    Top5MoviesTheme {
        AccountLayout(
            account = Account("test@test.com")
        )
    }
}