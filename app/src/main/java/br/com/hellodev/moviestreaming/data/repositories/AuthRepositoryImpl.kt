package br.com.hellodev.moviestreaming.data.repositories

import br.com.hellodev.moviestreaming.core.services.FirebaseService
import br.com.hellodev.moviestreaming.domain.repository.AuthRepository
import kotlinx.coroutines.suspendCancellableCoroutine

class AuthRepositoryImpl : AuthRepository {
    override suspend fun signUp(email: String, password: String) {
        return suspendCancellableCoroutine { block ->
            FirebaseService.getAuth()
                .createUserWithEmailAndPassword(email, password)
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

    override suspend fun signIn(email: String, password: String) {
        return suspendCancellableCoroutine { block ->
            FirebaseService.getAuth()
                .signInWithEmailAndPassword(email, password)
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