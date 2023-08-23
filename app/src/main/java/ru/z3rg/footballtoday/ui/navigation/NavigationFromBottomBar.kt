package ru.z3rg.footballtoday.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.z3rg.footballtoday.ui.Screen
import ru.z3rg.footballtoday.ui.screens.main.viewmodel.MainScreenViewModel
import ru.z3rg.footballtoday.ui.screens.webview.WebViewScreen

@Composable
fun navigationFromBottomBar(
    mainScreenViewModel: MainScreenViewModel
): NavHostController {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route
    ){
        composable(route = Screen.MainScreen.route) {
            navigationFromMain(mainScreenViewModel = mainScreenViewModel).currentDestination
        }
        composable(route = "web_view") {
            WebViewScreen()
        }
    }
    return navHostController
}