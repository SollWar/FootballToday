package ru.z3rg.footballtoday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.z3rg.footballtoday.ui.screens.main.MainScreen
import ru.z3rg.footballtoday.ui.screens.main.viewmodel.MainScreenViewModel
import ru.z3rg.footballtoday.ui.theme.FootballTodayTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTodayTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
                    val mainScreenState = mainScreenViewModel.state.collectAsState()
                    
                    MainScreen(state = mainScreenState.value)
                }
            }
        }
    }
}
