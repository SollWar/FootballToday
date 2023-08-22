package ru.z3rg.footballtoday.ui.screens.main.models

import ru.z3rg.domain.models.Livescore

sealed class MainScreenState {
    data class Display(
        var livescore: Livescore = Livescore()
    ): MainScreenState()
    data object Error: MainScreenState()
    data object Loading: MainScreenState()
}