package com.example.top5movies.ui.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.top5movies.ui.account.widget.AccountLayout

@Composable
fun AccountScreen(
    paddings: PaddingValues
) {
    val vm: AccountViewModel = hiltViewModel()
    val account by vm.state.collectAsState(initial = null)

    AccountLayout(
        paddings = paddings,
        account = account,
        onLogoutClick = vm::logout
    )

}