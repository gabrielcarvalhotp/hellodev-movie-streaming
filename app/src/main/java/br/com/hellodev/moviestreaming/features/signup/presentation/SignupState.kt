package br.com.hellodev.moviestreaming.features.signup.presentation

import br.com.hellodev.moviestreaming.core.input.InputType

data class SignupState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val invalidInputType: InputType? = null
)
