package br.com.hellodev.moviestreaming.features.signup.domain.usecases

import br.com.hellodev.moviestreaming.features.signup.domain.repository.SignupRepository

class SignupUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.signup(email, password)
    }
}