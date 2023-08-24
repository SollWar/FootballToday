package ru.z3rg.footballtoday.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.z3rg.footballtoday.ui.screens.main.viewmodel.MainScreenViewModel
import ru.z3rg.footballtoday.ui.screens.webview.WebViewScreen

@Composable
fun navigationFromBottomBar(
    mainScreenViewModel: MainScreenViewModel,
    onMain: () -> Unit = {},
    onWebView: () -> Unit = {},
    navHostController: NavHostController = rememberNavController()
): NavHostController {
    val navigationFromMain = navigationFromMain(mainScreenViewModel = mainScreenViewModel)

    NavHost(
        navController = navHostController,
        startDestination = "main"
    ){
        composable(route = "main") {
            onMain()
            navigationFromMain.currentDestination
        }
        composable(route = "web_view") {
            onWebView()
            WebViewScreen()
        }
    }
    return navHostController
}