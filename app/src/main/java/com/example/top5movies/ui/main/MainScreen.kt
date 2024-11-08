package com.example.top5movies.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.top5movies.ui.MainViewModel
import com.example.top5movies.ui.common.TextFieldDialog
import com.example.top5movies.ui.common.theme.Top5MoviesTheme
import com.example.top5movies.ui.main.widget.AppBottomBar
import com.example.top5movies.ui.main.widget.AppNavHost
import com.example.top5movies.ui.search.movie.SearchMovieDialog
import kotlin.math.truncate

@Composable
fun MainScreen(
    vm: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val openDialog = remember { mutableStateOf(false) }
    val backStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(
                visible = backStackEntry.value?.destination?.route == MainScreenPage.Home.route
            ) {
                FloatingActionButton(
                    onClick = { openDialog.value = true }
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = "")
                }
            }
        },
        bottomBar = { AppBottomBar(navController) }
    ) { paddings ->
        AppNavHost(
            navController = navController,
            paddings = paddings
        )
        if (openDialog.value)
            TextFieldDialog(
                title = "Create New List",
                label = "List Name",
                onDismiss = { openDialog.value = false },
                onResult = { name ->
                    openDialog.value = false
                    vm.addTop5List(name)
                }
            )
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    Top5MoviesTheme {
        MainScreen()
    }
}