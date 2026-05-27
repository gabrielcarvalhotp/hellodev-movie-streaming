package br.com.hellodev.moviestreaming.ui.features.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.preferences.AppPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class WelcomeViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(WelcomeState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = WelcomeState()
        )

    fun onAction(action: WelcomeAction) {
        when (action) {
            is WelcomeAction.OnNextScreen -> {
                saveWelcomeVisited()
            }
        }
    }

    private fun saveWelcomeVisited() {
        appPreferences.saveWelcomeAsVisited()
        _state.update { currentState ->
            currentState.copy(nextScreen = true)
        }
    }

}