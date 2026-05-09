package br.com.hellodev.moviestreaming.presenter.features.auth.signup

data class SignupState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false
)
