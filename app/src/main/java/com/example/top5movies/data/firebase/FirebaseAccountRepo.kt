package com.example.top5movies.data.firebase

import com.example.top5movies.domain.Account
import com.example.top5movies.domain.repo.AccountRepo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseAccountRepo @Inject constructor() : AccountRepo {

    private val auth by lazy { Firebase.auth }
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _currentAccount = MutableStateFlow<Account?>(null)
    override val currentAccount = _currentAccount.asStateFlow()

    init {
        Firebase.auth.addAuthStateListener(this::onAuthStateChange)
    }

    override suspend fun createAccount(email: String, password: String): Result<Account> = suspendCoroutine { cont ->
        val listener = OnCompleteListener<AuthResult> { task ->
            val user = if (task.isSuccessful) auth.currentUser else null
            if (user != null)
                cont.resume(Result.success(user.asAccount))
            else
                cont.resume(Result.failure(task.exception ?: Exception("User Not Found")))
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(listener)
    }

    override suspend fun signIn(email: String, password: String): Result<Account> = suspendCoroutine { cont ->
        val listener = OnCompleteListener<AuthResult> { task ->
            val user = if (task.isSuccessful) auth.currentUser else null
            if (user != null)
                cont.resume(Result.success(user.asAccount))
            else
                cont.resume(Result.failure(task.exception ?: Exception("User Not Found")))
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(listener)
    }

    override suspend fun logout() {
        auth.signOut()
    }

    private fun onAuthStateChange(auth: FirebaseAuth) {
        scope.launch {
            val account = auth.currentUser?.asAccount
            _currentAccount.value = account
        }
    }

    private val FirebaseUser.asAccount: Account
        get() = Account(
            email = this.email.orEmpty()
        )

}