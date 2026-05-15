package br.com.hellodev.moviestreaming.data.repositories

import br.com.hellodev.moviestreaming.core.services.FirebaseService
import br.com.hellodev.moviestreaming.domain.models.User
import br.com.hellodev.moviestreaming.domain.repository.UserRepository
import kotlinx.coroutines.suspendCancellableCoroutine

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