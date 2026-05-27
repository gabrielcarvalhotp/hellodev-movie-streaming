package br.com.hellodev.moviestreaming.ui.features.home

sealed interface HomeAction {
    data object OnSignOut: HomeAction
}