package br.com.hellodev.moviestreaming.domain.usecases

import br.com.hellodev.moviestreaming.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.signIn(email, password)
    }
}