package ru.z3rg.footballtoday.ui.screens.main.models

sealed class MainScreenEvent {
    data object ReloadLivescore: MainScreenEvent()
}