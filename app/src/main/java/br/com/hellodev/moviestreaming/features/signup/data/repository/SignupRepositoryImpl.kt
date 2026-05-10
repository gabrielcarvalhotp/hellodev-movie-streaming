package br.com.hellodev.moviestreaming.features.signup.data.repository

import br.com.hellodev.moviestreaming.core.services.FirebaseService
import br.com.hellodev.moviestreaming.features.signup.domain.repository.SignupRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.suspendCoroutine

class SignupRepositoryImpl : SignupRepository {
    override suspend fun signup(email: String, password: String) {
        return suspendCancellableCoroutine { block ->
            FirebaseService.getAuth().createUserWithEmailAndPassword(email, password)
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