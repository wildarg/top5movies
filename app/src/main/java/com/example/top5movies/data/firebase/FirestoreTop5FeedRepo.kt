package com.example.top5movies.data.firebase

import com.example.top5movies.domain.Account
import com.example.top5movies.domain.Top5
import com.example.top5movies.domain.repo.Top5FeedRepo
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.persistentCacheSettings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirestoreTop5FeedRepo @Inject constructor(
    private val mapper: Top5Mapper
) : Top5FeedRepo {

    private val currentUserEmail: String
        get() = Firebase.auth.currentUser?.email ?: throw IllegalStateException("No active session!")

    private val db by lazy {
        Firebase.firestore.apply {
            firestoreSettings = firestoreSettings {
                setLocalCacheSettings(persistentCacheSettings { })
            }
        }
    }

    private val currentUserDocRef: DocumentReference
        get() = db.collection(TOP_5_MOVIES_COLLECTION)
            .document(currentUserEmail)

    override val myFeed: Flow<List<Top5>> get() = callbackFlow {
        val listener = EventListener<DocumentSnapshot> { snapshot, error ->
            error?.printStackTrace()
            val feed = mapper.fromMap(snapshot?.data.orEmpty())
            trySend(feed)
        }

        val subscription = currentUserDocRef
            .addSnapshotListener(listener)

        awaitClose { subscription.remove() }
    }

    override suspend fun createTop5List(name: String): Result<Top5> = suspendCoroutine { cont ->
        currentUserDocRef
            .set(mapOf(name to emptyList<Map<String, Any>>()), SetOptions.merge())
            .addOnCompleteListener { task ->
                cont.resumeWithTaskResult(task) { Top5(name) }
            }
    }

    override suspend fun deleteTop5List(list: Top5): Result<Unit> = suspendCoroutine { cont ->
        currentUserDocRef
            .update(mapOf(list.title to FieldValue.delete()))
            .addOnCompleteListener { task ->
                cont.resumeWithTaskResult(task) { }
            }
    }

    override suspend fun renameTop5List(list: Top5, newName: String): Result<Unit> = suspendCoroutine { cont ->
        currentUserDocRef
            .update(
                mapOf(
                    newName to mapper.toMap(list.movies),
                    list.title to FieldValue.delete()
                )
            )
            .addOnCompleteListener { task ->
                cont.resumeWithTaskResult(task) { }
            }
    }

    override suspend fun updateTop5List(list: Top5): Result<Unit> = suspendCoroutine { cont ->
        currentUserDocRef
            .update(mapper.toMap(list))
            .addOnCompleteListener { task ->
                cont.resumeWithTaskResult(task) { }
            }
    }

    override suspend fun searchUsers(filter: String): Result<List<Account>> = suspendCoroutine { cont ->
        db.collection(TOP_5_MOVIES_COLLECTION)
            .get()
            .addOnCompleteListener { task ->
                cont.resumeWithTaskResult(task) { snapshot ->
                    snapshot.documents
                        .filter { filter.isNotBlank() && it.id.contains(filter, ignoreCase = true) }
                        .map { Account(email = it.id) }
                }
            }
    }

    override suspend fun getAccountFeed(account: Account): Result<List<Top5>> = suspendCoroutine { cont ->
        db.collection(TOP_5_MOVIES_COLLECTION)
            .document(account.email)
            .get()
            .addOnCompleteListener { task ->
                cont.resumeWithTaskResult(task) { mapper.fromMap(it.data.orEmpty()) }
            }
    }

    companion object {
        const val TOP_5_MOVIES_COLLECTION = "top_5_movies"
    }
}

private inline fun <T, reified R> Continuation<Result<T>>.resumeWithTaskResult(task: Task<R>, successResultBuilder: (R) -> T) {
    if (task.isSuccessful)
        resume(Result.success(successResultBuilder(task.result)))
    else
        resume(Result.failure(task.exception ?: Exception("Unknown error")))
}
