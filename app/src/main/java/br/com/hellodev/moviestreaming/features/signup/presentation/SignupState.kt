package br.com.hellodev.moviestreaming.features.signup.presentation

import br.com.hellodev.moviestreaming.core.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.input.InputType

data class SignupState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val invalidInputType: InputType? = null,
    val hasFeedback: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null,
)
