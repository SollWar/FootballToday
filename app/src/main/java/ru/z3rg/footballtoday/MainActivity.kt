package ru.z3rg.footballtoday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.z3rg.footballtoday.ui.bottombar.BottomAppBarComponent
import ru.z3rg.footballtoday.ui.bottombar.BottomMenuItem
import ru.z3rg.footballtoday.ui.navigation.navigationFromBottomBar
import ru.z3rg.footballtoday.ui.screens.main.viewmodel.MainScreenViewModel
import ru.z3rg.footballtoday.ui.theme.BackgroundGray
import ru.z3rg.footballtoday.ui.theme.FootballTodayTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTodayTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize(), color = BackgroundGray) {

                    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()

                    // Предзагрузка на SplashScreen
                    installSplashScreen().apply {
                        setKeepOnScreenCondition {
                            !mainScreenViewModel.displayed.value
                        }
                    }

                    var selectedItem by remember {
                        mutableStateOf("Home")
                    }

                    val mainHostController = navigationFromBottomBar(
                        mainScreenViewModel =  mainScreenViewModel,
                        onMain = {
                            selectedItem = "Home"
                        },
                        onWebView = {
                            selectedItem = "Web View"
                        }
                    )

                    val bottomMenuItemList = arrayListOf(
                        BottomMenuItem(
                            label = "Home", icon = Icons.Default.Home
                        ),
                        BottomMenuItem(
                            label = "Web View", icon = Icons.Default.Info
                        )
                    )

                    BottomAppBarComponent(
                        bottomMenuItemList = bottomMenuItemList,
                        item = selectedItem,
                        mainHostController = mainHostController
                    )
                }
            }
        }
    }
}
