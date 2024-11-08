 package com.example.top5movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top5movies.domain.repo.AccountRepo
import com.example.top5movies.domain.repo.Top5FeedRepo
import com.example.top5movies.ui.common.theme.Top5MoviesTheme
import com.example.top5movies.ui.main.MainScreen
import com.example.top5movies.ui.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

 @AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm: MainViewModel by viewModels()

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(0),
            navigationBarStyle = SystemBarStyle.dark(0),
        )
        setContent {
            val uiState by vm.state.collectAsState()

            Top5MoviesTheme {
                if (uiState.isLoggedIn)
                    MainScreen(vm)
                else
                    WelcomeScreen()
            }
        }
    }
}

data class MainActivityUiState(
    val isLoggedIn: Boolean = false
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepo: AccountRepo,
    private val feedRepo: Top5FeedRepo
) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityUiState())
    val state = _state.asStateFlow()

    init {
        subscribe()
    }

    private fun subscribe() {
        viewModelScope.launch {
            accountRepo.currentAccount.collect { acc ->
                _state.value = _state.value.copy(isLoggedIn = acc != null)
            }
        }
    }

    fun addTop5List(name: String) {
        viewModelScope.launch {
            val result = feedRepo.createTop5List(name)
            println("WILD:: $result")
        }
    }

}