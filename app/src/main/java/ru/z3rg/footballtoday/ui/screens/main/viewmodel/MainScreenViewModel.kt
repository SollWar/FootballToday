package ru.z3rg.footballtoday.ui.screens.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.z3rg.domain.repository.AllSportsRepository
import ru.z3rg.footballtoday.ui.screens.main.models.MainScreenEvent
import ru.z3rg.footballtoday.ui.screens.main.models.MainScreenState
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val allSportsRepository: AllSportsRepository
): ViewModel() {
    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Loading)
    val state = _state.asStateFlow()

    private val _displayed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val displayed = _displayed.asStateFlow()

    fun onState(mainScreenState: MainScreenState) {
        when (mainScreenState) {
            is MainScreenState.Display -> {

            }
            MainScreenState.Error -> {

            }
            MainScreenState.Loading -> {
                loadLivescore()
            }
        }
    }

    init {
        timeLimitPreLoad()
    }

    // Ограничение времени отображения Splash Screen
    private fun timeLimitPreLoad() = viewModelScope.launch {
        delay(700)
        _displayed.value = true
    }

    fun onEvent(mainScreenEvent: MainScreenEvent) {
        when (mainScreenEvent) {
            MainScreenEvent.ReloadLivescore -> {
                _state.value = MainScreenState.Loading
                loadLivescore()
            }
        }
    }

    private fun loadLivescore() {
        viewModelScope.launch {
            val responseLivescore = viewModelScope.async(Dispatchers.IO) {
                return@async allSportsRepository.getLivescore()
            }

            if (responseLivescore.await().body != null) {
                _state.value = MainScreenState.Display(
                    livescore = responseLivescore.await().body!!
                )
            } else {
                _state.value = MainScreenState.Error
                Log.d("Error Response Livescore", responseLivescore.await().errorMessage!!)
            }

        }
    }
}