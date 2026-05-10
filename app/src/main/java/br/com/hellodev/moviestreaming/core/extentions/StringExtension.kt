package br.com.hellodev.moviestreaming.core.extentions

import java.text.Normalizer
import java.util.Locale

fun String.isValidEmail(): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    return this.matches(emailRegex.toRegex())
}

fun String.normalize(): String {
    return Normalizer.normalize(this, Normalizer.Form.NFD)
        .replace(Regex("\\p{InCombiningDiacriticalMarks}"), "")
        .lowercase(Locale.getDefault())
}
