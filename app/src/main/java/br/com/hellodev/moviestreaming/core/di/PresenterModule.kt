package br.com.hellodev.moviestreaming.core.di

import br.com.hellodev.moviestreaming.ui.features.signin.SignInViewModel
import br.com.hellodev.moviestreaming.ui.features.signup.SignUpViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val presenterModule = module {
    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
}