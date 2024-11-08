package com.example.top5movies.domain.repo

import com.example.top5movies.domain.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepo {
    val currentAccount: Flow<Account?>
    suspend fun createAccount(email: String, password: String): Result<Account>
    suspend fun signIn(email: String, password: String): Result<Account>
    suspend fun logout()
}