package br.com.hellodev.moviestreaming.presenter.features.auth.signup

import br.com.hellodev.moviestreaming.core.input.InputType

data class SignupState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val invalidInputType: InputType? = null
)
