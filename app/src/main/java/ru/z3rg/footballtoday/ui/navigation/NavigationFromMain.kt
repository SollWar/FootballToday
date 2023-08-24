package ru.z3rg.footballtoday.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.z3rg.domain.models.Event
import ru.z3rg.footballtoday.ui.screens.Screen
import ru.z3rg.footballtoday.ui.screens.details.DetailsScreen
import ru.z3rg.footballtoday.ui.screens.details.viewmodel.DetailsViewModel
import ru.z3rg.footballtoday.ui.screens.main.MainScreenDisplay
import ru.z3rg.footballtoday.ui.screens.main.MainScreenError
import ru.z3rg.footballtoday.ui.screens.main.MainScreenLoading
import ru.z3rg.footballtoday.ui.screens.main.models.MainScreenState
import ru.z3rg.footballtoday.ui.screens.main.viewmodel.MainScreenViewModel

@Composable
fun navigationFromMain(
    mainScreenViewModel: MainScreenViewModel
): NavHostController{
    val navHostController = rememberNavController()
    val mainScreenState = mainScreenViewModel.state.collectAsState()

    NavHost(
        navController = navHostController,
        route = "main_route",
        startDestination = Screen.MainScreen.route
    ) {
        var eventItemDetail = Event()
        composable(route = Screen.MainScreen.route) {
            when (mainScreenState.value) {
                is MainScreenState.Display -> {
                    MainScreenDisplay(
                        onState = mainScreenViewModel::onState,
                        state = mainScreenState.value as MainScreenState.Display,
                        onItemClick = {
                            eventItemDetail = it
                            navHostController.navigate(Screen.DetailsScreen.route)
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
            val detailsViewModel: DetailsViewModel = hiltViewModel()
            val detailsState = detailsViewModel.state.collectAsState()
            if (detailsState.value.event.eventStatus == "") {
                detailsViewModel.saveState(eventItemDetail)
            }
            DetailsScreen(
                state = detailsState.value
            )
        }
    }
    return navHostController
}