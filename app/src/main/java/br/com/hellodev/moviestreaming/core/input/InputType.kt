package br.com.hellodev.moviestreaming.core.input

import br.com.hellodev.moviestreaming.R

enum class InputType {
    EMAIL,
    PASSWORD,
    FIRST_NAME,
    SURNAME,
    PHONE,
    GENRE,
    COUNTRY
}

fun InputType?.inputErrorMessage(): Int {
    return when (this) {
        InputType.EMAIL -> R.string.error_email_invalid
        InputType.PASSWORD -> R.string.error_password_invalid
        InputType.FIRST_NAME -> R.string.error_first_name_invalid
        InputType.SURNAME -> R.string.error_surname_invalid
        InputType.PHONE -> R.string.error_phone_invalid
        InputType.GENRE -> R.string.error_genre_invalid
        InputType.COUNTRY -> R.string.error_country_invalid
        else -> R.string.error_generic
    }
}