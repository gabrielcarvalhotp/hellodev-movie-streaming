package br.com.hellodev.moviestreaming.domain.repository

interface AuthRepository {
    suspend fun signUp(email: String, password: String)
    suspend fun signIn(email: String, password: String)
}