package com.example.top5movies.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top5movies.domain.repo.AccountRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    val repo: AccountRepo
): ViewModel() {

    val state = repo.currentAccount

    fun logout() {
        viewModelScope.launch {
            repo.logout()
        }
    }
}