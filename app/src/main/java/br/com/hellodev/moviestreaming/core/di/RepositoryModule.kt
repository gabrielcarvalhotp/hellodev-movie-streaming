package br.com.hellodev.moviestreaming.core.di

import br.com.hellodev.moviestreaming.data.repositories.AuthRepositoryImpl
import br.com.hellodev.moviestreaming.data.repositories.UserRepositoryImpl
import br.com.hellodev.moviestreaming.domain.repository.AuthRepository
import br.com.hellodev.moviestreaming.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl() }
    factory<UserRepository> { UserRepositoryImpl() }
}