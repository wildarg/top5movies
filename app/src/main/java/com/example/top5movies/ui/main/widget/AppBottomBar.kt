package com.example.top5movies.ui.main.widget

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.top5movies.ui.common.theme.Top5MoviesTheme
import com.example.top5movies.ui.main.MainScreenPage

@Composable
fun AppBottomBar(
    navController: NavHostController = rememberNavController()
) {
    val pages = listOf(
        MainScreenPage.Home,
        MainScreenPage.Search,
        MainScreenPage.Account
    )

    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar {
        pages.forEach { page ->
            NavigationBarItem(
                selected = backStackEntry.value?.destination?.route == page.route,
                onClick = { navController.navigate(page.route) },
                label = { Text(page.label) },
                icon = { Icon(page.icon, contentDescription = null) }
            )
        }
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    Top5MoviesTheme {
        AppBottomBar()
    }
}

