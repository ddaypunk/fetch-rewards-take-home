package com.ddaypunk.fetchrewardsexercise.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddaypunk.fetchrewardsexercise.data.model.HiringDataModel
import com.ddaypunk.fetchrewardsexercise.data.service.HiringDataRepository
import com.ddaypunk.fetchrewardsexercise.ui.component.ListCardState
import dagger.hilt.android.lifecycle.HiltViewModel
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
            val state = mapDataToState(data)
            _uiState.update { UiState.Success(state) }
        }
    }

    private fun mapDataToState(data: Map<String, List<HiringDataModel>>): List<ListCardState> {
        return data.map { entry ->
            ListCardState(
                title = entry.key,
                isExpanded = false,
                entries = entry.value.mapNotNull { it.name }
            )
        }
    }
}

sealed class UiState {
    data object Loading : UiState()
    data object Error : UiState()
    data class Success(val cardStates: List<ListCardState>) : UiState()
}
