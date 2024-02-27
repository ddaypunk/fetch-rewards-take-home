package com.ddaypunk.fetchrewardsexercise.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddaypunk.fetchrewardsexercise.data.model.HiringDataModel
import com.ddaypunk.fetchrewardsexercise.data.service.HiringDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: HiringDataRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val data = repository.retrieve()
            delay(2_000) // simulate loading state
            _uiState.update { UiState.Success(data) }
        }
    }
}
sealed class UiState {
    data object Loading : UiState()
    data object Error : UiState()
    data class Success(val data: Map<String, List<HiringDataModel>>) : UiState()
}
