package br.com.hellodev.moviestreaming.features.signup.data.repository

import br.com.hellodev.moviestreaming.features.signup.domain.repository.SignupRepository

class SignupRepositoryImpl : SignupRepository {
    override suspend fun signup(email: String, password: String) {
        TODO("Not yet implemented")
    }
}