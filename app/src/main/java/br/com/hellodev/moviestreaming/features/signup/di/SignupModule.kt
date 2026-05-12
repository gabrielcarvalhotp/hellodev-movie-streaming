package br.com.hellodev.moviestreaming.features.signup.di

import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.hellodev.moviestreaming.features.signup.data.repository.SignupRepositoryImpl
import br.com.hellodev.moviestreaming.features.signup.data.repository.UserRepositoryImpl
import br.com.hellodev.moviestreaming.features.signup.domain.repository.SignupRepository
import br.com.hellodev.moviestreaming.features.signup.domain.repository.UserRepository
import br.com.hellodev.moviestreaming.features.signup.domain.usecases.SaveUserUseCase
import br.com.hellodev.moviestreaming.features.signup.domain.usecases.SignupUseCase
import br.com.hellodev.moviestreaming.features.signup.presentation.SignupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val signupModule = module {
    factory<SignupRepository> { SignupRepositoryImpl() }
    factory<UserRepository> { UserRepositoryImpl() }
    factory<SignupUseCase> { SignupUseCase(repository = get()) }
    factory<SaveUserUseCase> { SaveUserUseCase(repository = get()) }
    viewModelOf(::SignupViewModel)
}