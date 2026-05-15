package br.com.hellodev.moviestreaming.domain.usecases

import br.com.hellodev.moviestreaming.domain.models.User
import br.com.hellodev.moviestreaming.domain.repository.UserRepository

class SaveUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.saveUser(user)
    }
}