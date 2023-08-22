package ru.z3rg.footballtoday.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.z3rg.footballtoday.ui.screens.main.MainScreenDisplay
import ru.z3rg.footballtoday.ui.screens.main.MainScreenError
import ru.z3rg.footballtoday.ui.screens.main.MainScreenLoading
import ru.z3rg.footballtoday.ui.screens.main.models.MainScreenState
import ru.z3rg.footballtoday.ui.screens.main.viewmodel.MainScreenViewModel

@Composable
fun Navigation(
    mainScreenViewModel: MainScreenViewModel
) {
    val navHostController = rememberNavController()
    val mainScreenState = mainScreenViewModel.state.collectAsState()

    NavHost(navController = navHostController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            when (mainScreenState.value) {
                is MainScreenState.Display -> {
                    MainScreenDisplay(
                        onState = mainScreenViewModel::onState,
                        state = mainScreenState.value as MainScreenState.Display,
                        onItemClick = {

                        },
                    )
                }
                MainScreenState.Error -> {
                    MainScreenError(
                        onState = mainScreenViewModel::onState,
                        onReloadClick = mainScreenViewModel::onEvent
                    )
                }
                MainScreenState.Loading -> {
                    MainScreenLoading(
                        onState = mainScreenViewModel::onState
                    )
                }
            }
        }
        composable(route = Screen.DetailsScreen.route) {

        }
    }
}