package com.example.top5movies.ui.search.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top5movies.domain.Account
import com.example.top5movies.domain.Top5
import com.example.top5movies.domain.repo.Top5FeedRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

data class SearchUserUIState(
    val users: List<Account> = emptyList(),
    val activeFeed: List<Top5>? = null
)

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val repo: Top5FeedRepo
) : ViewModel() {

    private val filter = MutableStateFlow("")
    private val _state = MutableStateFlow(SearchUserUIState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            filter.debounce(300.milliseconds).collect { text ->
                _state.value = SearchUserUIState(
                    users = getUsersByFilter(text)
                )
            }
        }
    }

    fun onFilterChange(text: String) {
        viewModelScope.launch { filter.emit(text) }
    }

    fun openAccountFeed(account: Account) {
        viewModelScope.launch {
            val result = repo.getAccountFeed(account)
            val feed = result.getOrNull().orEmpty()
            _state.value = _state.value.copy(activeFeed = feed)
        }
    }

    fun closeFeed() {
        _state.value = _state.value.copy(activeFeed = null)
    }

    private suspend fun getUsersByFilter(filter: String): List<Account> {
        return repo.searchUsers(filter).getOrNull().orEmpty()
    }

}