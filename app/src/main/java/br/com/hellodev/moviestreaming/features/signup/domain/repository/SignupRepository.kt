package br.com.hellodev.moviestreaming.features.signup.domain.repository

interface SignupRepository {
    suspend fun signup(email: String, password: String)
}