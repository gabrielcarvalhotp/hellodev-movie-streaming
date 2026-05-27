package br.com.hellodev.moviestreaming.ui.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.preferences.AppPreferences
import br.com.hellodev.moviestreaming.core.services.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SplashViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(SplashState())
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
            initialValue = SplashState()
        )

    fun onAction(action: SplashAction) {
        when (action) {
            is SplashAction.OnNextScreen -> {
                _state.update { currentState -> currentState.copy(isLoading = true) }
                getWelcomeVisited()
            }
            else -> TODO("Handle actions")
        }
    }

    private fun getWelcomeVisited() {
        _state.update { currentState ->
            currentState.copy(
                isWelcomeVisited = appPreferences.isWelcomeVisited(),
                isLoading = false,
                isAuthenticated = FirebaseService.isAuthenticated()
            )
        }
    }

}