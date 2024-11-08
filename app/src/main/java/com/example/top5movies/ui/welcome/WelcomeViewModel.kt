package com.example.top5movies.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top5movies.domain.repo.AccountRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class WelcomeScreenEffect {
    data class ShowToast(val text: String) : WelcomeScreenEffect()
}

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repo: AccountRepo
) : ViewModel() {

    private val _effect = Channel<WelcomeScreenEffect>()
    val effect = _effect.receiveAsFlow()

    fun createAccount(email: String, password: String) {
        viewModelScope.launch {
            val result = repo.createAccount(email, password)
            if (result.isFailure)
                showError(result.exceptionOrNull()?.message)
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val result = repo.signIn(email, password)
            if (result.isFailure)
                showError(result.exceptionOrNull()?.message)
        }
    }

    private fun showError(msg: String?) {
        viewModelScope.launch {
            _effect.send(WelcomeScreenEffect.ShowToast(msg ?: "Unknown error"))
        }
    }


}