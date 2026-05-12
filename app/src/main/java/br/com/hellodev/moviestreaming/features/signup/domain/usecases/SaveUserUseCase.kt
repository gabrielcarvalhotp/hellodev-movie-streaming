package br.com.hellodev.moviestreaming.features.signup.domain.usecases

import br.com.hellodev.moviestreaming.features.signup.domain.model.User
import br.com.hellodev.moviestreaming.features.signup.domain.repository.UserRepository

class SaveUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.saveUser(user)
    }
}