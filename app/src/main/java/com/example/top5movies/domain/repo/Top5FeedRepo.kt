package com.example.top5movies.domain.repo

import com.example.top5movies.domain.Account
import com.example.top5movies.domain.Top5
import kotlinx.coroutines.flow.Flow

interface Top5FeedRepo {
    val myFeed: Flow<List<Top5>>
    suspend fun createTop5List(name: String): Result<Top5>
    suspend fun deleteTop5List(list: Top5): Result<Unit>
    suspend fun renameTop5List(list: Top5, newName: String): Result<Unit>
    suspend fun updateTop5List(list: Top5): Result<Unit>
    suspend fun searchUsers(filter: String): Result<List<Account>>
    suspend fun getAccountFeed(account: Account): Result<List<Top5>>
}