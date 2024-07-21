package com.example.top5movies.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainScreenPage(val route: String,
                            val label: String,
                            val icon: ImageVector
) {
    data object Home: MainScreenPage(
        route="home",
        label="Home",
        icon= Icons.Rounded.Home
    )

    data object Search: MainScreenPage(
        route="search",
        label="Search",
        icon= Icons.Rounded.Search
    )

    data object Account: MainScreenPage(
        route="account",
        label="Account",
        icon= Icons.Rounded.Person
    )
}