package ru.z3rg.footballtoday.ui.screens.details.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.z3rg.domain.models.Event
import ru.z3rg.footballtoday.ui.screens.details.models.DetailsScreenState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<DetailsScreenState> = MutableStateFlow(DetailsScreenState(Event()))
    val state = _state.asStateFlow()

    fun saveState(event: Event) {
        _state.value.event = event
    }
}