package br.com.hellodev.moviestreaming.features.signup.data.repository

import br.com.hellodev.moviestreaming.core.services.FirebaseService
import br.com.hellodev.moviestreaming.features.signup.domain.model.User
import br.com.hellodev.moviestreaming.features.signup.domain.repository.UserRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl: UserRepository {
    override suspend fun saveUser(user: User) {
        return suspendCancellableCoroutine { block ->
            FirebaseService.getDatabase()
                .child("users")
                .child(FirebaseService.getUserId())
                .setValue(user)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { exception ->
                            block.resumeWith(Result.failure(exception))
                        }
                        return@addOnCompleteListener
                    }

                    block.resumeWith(Result.success(Unit))
                }
        }
    }
}