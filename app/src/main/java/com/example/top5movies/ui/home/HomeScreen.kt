package com.example.top5movies.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top5movies.domain.Movie
import com.example.top5movies.domain.Top5
import com.example.top5movies.domain.repo.Top5FeedRepo
import com.example.top5movies.ui.common.TextFieldDialog
import com.example.top5movies.ui.home.widget.HomeLayout
import com.example.top5movies.ui.home.widget.MovieDetailsDialog
import com.example.top5movies.ui.search.movie.SearchMovieDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    paddings: PaddingValues
) {
    val context = LocalContext.current
    val feed by vm.feed.collectAsState(initial = emptyList())
    val renameItem = remember { mutableStateOf<Top5?>(null) }
    val top5 = remember { mutableStateOf<Top5?>(null) }
    val deleteMovieRequest = remember { mutableStateOf<DeleteMovieRequest?>(null) }

    LaunchedEffect(key1 = vm) {
        vm.effect.collect { effect ->
            when (effect) {
                is HomeScreenEffect.ShowToast ->
                    Toast.makeText(context, effect.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    HomeLayout(
        paddings = paddings,
        feed = feed,
        onDelete = vm::deleteTop5,
        onRename = { renameItem.value = it },
        onAddMovie = { top5.value = it },
        onMovieClick = { list, movie ->  deleteMovieRequest.value = DeleteMovieRequest(list, movie) }
    )

    if (renameItem.value != null)
        TextFieldDialog(
            title = "Rename Top5 List",
            label = "List Name",
            value = renameItem.value!!.title,
            onDismiss = { renameItem.value = null },
            onResult = { newName ->
                vm.renameTop5List(renameItem.value!!, newName)
                renameItem.value = null
            }
        )

    if (top5.value != null)
        SearchMovieDialog(
            onSelectMovie = { movie -> vm.addMovieToList(top5.value!!, movie) },
            onDismissRequest = { top5.value = null }
        )

    deleteMovieRequest.value?.let { request ->
        MovieDetailsDialog(
            movie = request.movie,
            onDismissRequest = { deleteMovieRequest.value = null },
            onDeleteRequest = {
                vm.deleteMovieFromList(request.top5, request.movie)
                deleteMovieRequest.value = null
            }
        )
    }
}

data class DeleteMovieRequest(
    val top5: Top5,
    val movie: Movie
)

sealed class HomeScreenEffect {
    data class ShowToast(val text: String) : HomeScreenEffect()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val feedRepo: Top5FeedRepo
) : ViewModel() {

    private val _effect = Channel<HomeScreenEffect>()
    val effect = _effect.receiveAsFlow()

    val feed: Flow<List<Top5>>
        get() = feedRepo.myFeed

    fun deleteTop5(top5: Top5) {
        viewModelScope.launch {
            val result = feedRepo.deleteTop5List(top5)
            if (result.isFailure)
                showError(result.exceptionOrNull()?.message)
        }
    }

    fun renameTop5List(top5: Top5, newName: String) {
        viewModelScope.launch {
            val result = feedRepo.renameTop5List(top5, newName)
            if (result.isFailure)
                showError(result.exceptionOrNull()?.message)
        }
    }

    fun addMovieToList(top5: Top5, movie: Movie) {
        viewModelScope.launch {
            if (top5.hasMovie(movie)) {
                _effect.send(HomeScreenEffect.ShowToast("Already in list!"))
                return@launch
            }
            val result = feedRepo.updateTop5List(
                top5.copy(movies = top5.movies + listOf(movie))
            )
            if (result.isFailure)
                showError(result.exceptionOrNull()?.message)
        }
    }

    fun deleteMovieFromList(top5: Top5, movie: Movie) {
        viewModelScope.launch {
            val result = feedRepo.updateTop5List(
                top5.copy(movies = top5.movies.filter { it.id != movie.id })
            )
            if (result.isFailure)
                showError(result.exceptionOrNull()?.message)
        }
    }

    private fun showError(msg: String?) {
        viewModelScope.launch {
            _effect.send(HomeScreenEffect.ShowToast(msg ?: "Unknown error"))
        }
    }
}