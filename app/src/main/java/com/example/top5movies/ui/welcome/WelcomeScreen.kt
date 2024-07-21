package com.example.top5movies.ui.welcome

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.top5movies.ui.welcome.widget.WelcomeLayout

@Composable
fun WelcomeScreen(
    vm: WelcomeViewModel = viewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = vm) {
        vm.effect.collect { effect ->
            when (effect) {
                is WelcomeScreenEffect.ShowToast    ->
                    Toast.makeText(context, effect.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    WelcomeLayout(
        onSignUp = vm::createAccount,
        onSignIn = vm::signIn
    )
}