package br.com.hellodev.moviestreaming.domain.repository

import br.com.hellodev.moviestreaming.domain.models.User

interface UserRepository {
    suspend fun saveUser(user: User);
}