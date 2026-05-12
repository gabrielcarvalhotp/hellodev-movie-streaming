package br.com.hellodev.moviestreaming.features.signup.domain.repository

import br.com.hellodev.moviestreaming.features.signup.domain.model.User

interface UserRepository {
    suspend fun saveUser(user: User);
}