package br.com.hellodev.moviestreaming.core.di

import br.com.hellodev.moviestreaming.domain.usecases.SaveUserUseCase
import br.com.hellodev.moviestreaming.domain.usecases.SignInUseCase
import br.com.hellodev.moviestreaming.domain.usecases.SignUpUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory<SignUpUseCase> { SignUpUseCase(repository = get()) }
    factory<SignInUseCase> { SignInUseCase(repository = get()) }

    factory<SaveUserUseCase> { SaveUserUseCase(repository = get()) }
}