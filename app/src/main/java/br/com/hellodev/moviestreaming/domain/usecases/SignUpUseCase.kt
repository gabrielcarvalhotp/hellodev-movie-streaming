package br.com.hellodev.moviestreaming.domain.usecases

import br.com.hellodev.moviestreaming.domain.repository.AuthRepository

class SignUpUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.signUp(email, password)
    }
}