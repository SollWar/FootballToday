package ru.z3rg.footballtoday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.z3rg.footballtoday.ui.Screen
import ru.z3rg.footballtoday.ui.navigation.navigationFromBottomBar
import ru.z3rg.footballtoday.ui.screens.main.viewmodel.MainScreenViewModel
import ru.z3rg.footballtoday.ui.theme.BackgroundGray
import ru.z3rg.footballtoday.ui.theme.FootballTodayTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTodayTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = BackgroundGray) {

                    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()

                    installSplashScreen().apply {
                        setKeepOnScreenCondition {
                            !mainScreenViewModel.displayed.value
                        }
                    }

                    data class BottomMenuItem(val label: String, val icon: ImageVector)

                    fun prepareBottomMenu(): List<BottomMenuItem> {
                        val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

                        bottomMenuItemsList.add(BottomMenuItem(label = "Home", icon = Icons.Filled.Home))
                        bottomMenuItemsList.add(BottomMenuItem(label = "Web View", icon = Icons.Filled.Info))

                        return bottomMenuItemsList
                    }

                    var selectedItem by remember {
                        mutableStateOf("Home")
                    }

                    val navigation = navigationFromBottomBar(mainScreenViewModel)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        BottomAppBar(
                            modifier = Modifier
                                .align(alignment = Alignment.BottomCenter)
                        ) {
                            prepareBottomMenu().forEach {
                                this.NavigationBarItem(
                                    selected = (selectedItem == it.label),
                                    onClick = {
                                        selectedItem = it.label
                                        when (selectedItem) {
                                            "Home" -> {
                                                navigation.navigate(Screen.MainScreen.route) {
                                                    popUpTo(Screen.MainScreen.route) {
                                                        inclusive = true
                                                    }
                                                }
                                            }

                                            "Web View" -> {
                                                navigation.navigate("web_view") {
                                                    popUpTo("web_view") {
                                                        inclusive = true
                                                    }
                                                }
                                            }
                                        }
                                    },
                                    icon = {
                                        Icon(imageVector = it.icon, contentDescription = it.label)
                                    },
                                    label = {
                                        Text(text = it.label)
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}
