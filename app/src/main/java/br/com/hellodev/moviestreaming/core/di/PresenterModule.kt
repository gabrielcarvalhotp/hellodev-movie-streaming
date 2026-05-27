package br.com.hellodev.moviestreaming.core.di

import br.com.hellodev.moviestreaming.ui.features.home.HomeViewModel
import br.com.hellodev.moviestreaming.ui.features.auth.AuthViewModel
import br.com.hellodev.moviestreaming.ui.features.splash.SplashViewModel
import br.com.hellodev.moviestreaming.ui.features.welcome.WelcomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val presenterModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::HomeViewModel)
}