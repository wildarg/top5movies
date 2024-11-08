package com.example.top5movies.ui.welcome.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.R
import com.example.top5movies.ui.common.theme.Top5MoviesTheme
import com.example.top5movies.ui.common.theme.Typography

@Composable
fun WelcomeLayout(
    onSignUp: (email: String, password: String) -> Unit = {_, _ -> },
    onSignIn: (email: String, password: String) -> Unit = {_, _ -> },
) {

    var hasAccount by rememberSaveable { mutableStateOf(true) }

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.top_movies_back),
            contentDescription = ""
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.2f),
                            Color.Black,
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 36.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Top 5 Movies",
                style = Typography.displayMedium.copy(color = Color.White)
            )
            if (hasAccount)
                SignInCard(
                    onSignInClick = onSignIn,
                    onSignUpRequest = { hasAccount = false }
                )
            else
                SignUpCard(
                    onSignUp = onSignUp,
                    onSignInRequest = { hasAccount = true }
                )
        }
    }
}

@Preview
@Composable
private fun WelcomeLayoutPreview() {
    Top5MoviesTheme {
        WelcomeLayout()
    }
}