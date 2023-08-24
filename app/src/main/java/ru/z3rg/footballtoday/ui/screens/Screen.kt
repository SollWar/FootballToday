package ru.z3rg.footballtoday.ui.screens

sealed class Screen(val route: String) {
    data object MainScreen: Screen("main_screen")
    data object DetailsScreen: Screen("details_screen")
}