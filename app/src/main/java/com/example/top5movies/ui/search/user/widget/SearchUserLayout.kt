package com.example.top5movies.ui.search.user.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.domain.Account
import com.example.top5movies.ui.common.MainTextField
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun SearchUserLayout(
    paddings: PaddingValues = PaddingValues(8.dp),
    users: List<Account>,
    onFilterChange: (String) -> Unit = {},
    onAccountClick: (Account) -> Unit = {}
) {
    var filter by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(paddings)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MainTextField(
            value = filter,
            leadingIcon = Icons.Rounded.Person,
            onValueChange = {
                filter = it
                onFilterChange(it)
            }
        )
        LazyColumn(
            modifier = Modifier.weight(1.0f)
        ) {
            items(users) { user ->
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onAccountClick(user) }
                        .padding(vertical = 16.dp, horizontal = 8.dp),
                    text = user.email,
                    style = MaterialTheme.typography.headlineSmall
                        .copy(color = MaterialTheme.colorScheme.onBackground)
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchUserLayoutPreview() {
    Top5MoviesTheme {
        SearchUserLayout(users = listOf(Account("test@test.com")))
    }
}