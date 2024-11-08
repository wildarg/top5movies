package com.example.top5movies.ui.main.widget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.top5movies.ui.account.AccountScreen
import com.example.top5movies.ui.account.widget.AccountLayout
import com.example.top5movies.ui.home.HomeScreen
import com.example.top5movies.ui.main.MainScreenPage
import com.example.top5movies.ui.search.user.SearchUserScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    paddings: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenPage.Home.route
    ) {
        composable(route = MainScreenPage.Home.route) {
            HomeScreen(paddings = paddings)
        }
        composable(route = MainScreenPage.Search.route) {
            SearchUserScreen(paddings = paddings)
        }
        composable(route = MainScreenPage.Account.route) {
            AccountScreen(paddings = paddings)
        }
    }
}