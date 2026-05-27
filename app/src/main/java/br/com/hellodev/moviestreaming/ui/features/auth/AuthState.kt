package br.com.hellodev.moviestreaming.ui.features.auth

import br.com.hellodev.moviestreaming.core.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.input.InputType

data class AuthState(
    val isLoading: Boolean = false,
    val hasFeedback: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null,

    val email: String = "",
    val emailError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val passwordVisibility: Boolean = false,
    val isAuthenticated: Boolean = false,
)
