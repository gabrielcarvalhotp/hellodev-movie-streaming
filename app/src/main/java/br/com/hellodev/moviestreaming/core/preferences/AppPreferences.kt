package br.com.hellodev.moviestreaming.core.preferences

import android.content.Context
import androidx.core.content.ContextCompat.getString
import br.com.hellodev.moviestreaming.R
import androidx.core.content.edit

class AppPreferences(context: Context) {

    private val sharedPref = context.getSharedPreferences(
        getString(context,R.string.preferences_file_name), Context.MODE_PRIVATE)
    private val welcomeVisited = getString(context,R.string.preferences_welcome_visited)

    fun saveWelcomeAsVisited() {
        sharedPref.edit {
            putBoolean(welcomeVisited, true)
        }
    }

    fun isWelcomeVisited() = sharedPref.getBoolean(welcomeVisited, false)
}