package com.example.top5movies.ui.welcome.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.ui.common.MainButton
import com.example.top5movies.ui.common.MainTextField
import com.example.top5movies.ui.common.SecondButton
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun SignInCard(
    onSignInClick: (email: String, password: String) -> Unit = {_, _ -> },
    onSignUpRequest: () -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MainTextField(
            value = email,
            label = "Email",
            leadingIcon = Icons.Rounded.Person,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it }
        )
        MainTextField(
            value = password,
            label = "Password",
            leadingIcon = Icons.Rounded.Lock,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(36.dp))
        MainButton(
            text = "Sing In",
            onClick = { onSignInClick(email, password) }
        )
        SecondButton(text = "Don't have an account?", onClick = onSignUpRequest)
    }
}

@Preview
@Composable
private fun SignInCardPreview() {
    Top5MoviesTheme {
        SignInCard()
    }
}