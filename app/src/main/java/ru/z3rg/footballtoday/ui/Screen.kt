package ru.z3rg.footballtoday.ui

sealed class Screen(val route: String) {
    data object MainScreen: Screen("main_screen")
    data object DetailsScreen: Screen("details_screen")
}