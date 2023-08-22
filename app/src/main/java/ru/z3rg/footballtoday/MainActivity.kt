package ru.z3rg.footballtoday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.z3rg.footballtoday.ui.Navigation
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

                    //WebViewScreen()
                    Navigation(mainScreenViewModel)

                }
            }
        }
    }
}
