package ru.z3rg.footballtoday.ui.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.z3rg.domain.models.Livescore
import ru.z3rg.domain.repository.AllSportsRepository
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val allSportsRepository: AllSportsRepository
): ViewModel() {
    private val _state: MutableStateFlow<Livescore> = MutableStateFlow(Livescore())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val responseLivescore = viewModelScope.async(Dispatchers.IO) {
                return@async allSportsRepository.getLivescore()
            }


            _state.value = responseLivescore.await().body!!
        }
    }
}