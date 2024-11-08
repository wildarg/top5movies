package com.example.top5movies.ui.search.user

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.top5movies.domain.Account
import com.example.top5movies.ui.search.user.widget.AccountFeedDialog
import com.example.top5movies.ui.search.user.widget.SearchUserLayout

@Composable
fun SearchUserScreen(
    paddings: PaddingValues = PaddingValues(8.dp)
) {

    val vm: SearchUserViewModel = hiltViewModel()
    val state by vm.state.collectAsState()

    SearchUserLayout(
        paddings = paddings,
        users = state.users,
        onFilterChange = vm::onFilterChange,
        onAccountClick = vm::openAccountFeed
    )

    val account = state.activeAccount
    val feed = state.activeFeed
    if (feed != null && account != null)
        AccountFeedDialog(
            account = account,
            feed = feed,
            onDismissRequest = vm::closeFeed
        )
}