package br.com.hellodev.moviestreaming.domain.usecases

import br.com.hellodev.moviestreaming.core.services.FirebaseService
import br.com.hellodev.moviestreaming.domain.repository.AuthRepository

class SignOutUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.signOut()
    }
}