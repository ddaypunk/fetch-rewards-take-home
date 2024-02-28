package com.ddaypunk.fetchrewardsexercise.hiring.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddaypunk.fetchrewardsexercise.core.client.ApiResponse
import com.ddaypunk.fetchrewardsexercise.hiring.data.model.HiringDataModel
import com.ddaypunk.fetchrewardsexercise.hiring.data.repository.HiringDataRepository
import com.ddaypunk.fetchrewardsexercise.hiring.presentation.component.ListCardState
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
            val response = repository.retrieve()

            delay(2_000) // to simulate loading state
            when (response) {
                is ApiResponse.Error -> {
                    response.throwable?.message?.let { nonNullMessage ->
                        _uiState.update { UiState.Error(nonNullMessage) }
                    }
                }

                is ApiResponse.Success -> {
                    response.data?.let { nonNullResponseData ->
                        val state = mapDataToState(nonNullResponseData)
                        _uiState.update { UiState.Ready(state) }
                    }
                }
            }
        }
    }

    private fun onUserInput(userInput: UserInput) =
        when (userInput) {
            is UserInput.CardToggled -> handleCardToggled(userInput.id)
        }

    private fun handleCardToggled(id: String) =
        _uiState.update { uiState ->
            val cardStates = (uiState as UiState.Ready).cardStates.toMutableList()
            val index = cardStates.indexOfFirst { card -> card.title == id }
            val newCard = cardStates[index].copy(isExpanded = !cardStates[index].isExpanded)
            cardStates[index] = newCard

            uiState.copy(
                cardStates = cardStates.toList()
            )
        }

    private fun mapDataToState(data: Map<String, List<HiringDataModel>>) =
        data.map { entry ->
            ListCardState(
                title = entry.key,
                isExpanded = false,
                entries = entry.value.mapNotNull { it.name },
                onClick = { onUserInput(UserInput.CardToggled(entry.key)) }
            )
        }
}

sealed class UiState {
    data object Loading : UiState()
    data class Error(val error: String) : UiState()
    data class Ready(val cardStates: List<ListCardState>) : UiState()
}

sealed class UserInput {
    data class CardToggled(val id: String) : UserInput()
}
