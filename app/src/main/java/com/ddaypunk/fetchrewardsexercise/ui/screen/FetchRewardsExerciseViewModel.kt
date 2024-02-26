package com.ddaypunk.fetchrewardsexercise.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddaypunk.fetchrewardsexercise.data.model.FetchHiringDataModel
import com.ddaypunk.fetchrewardsexercise.data.service.FetchHiringDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FetchRewardsExerciseViewModel(
//    private val repository: FetchHiringDataRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(emptyMap()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    private val repository = FetchHiringDataRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.retrieve()
            _uiState.update {
                it.copy(data = data)
            }
        }
    }
}

data class UiState(
    val data: Map<String, List<FetchHiringDataModel>>
)

//sealed class UiState {
//    data object Loading : UiState()
//    data object Error : UiState()
//    data class Success(val data: List<FetchHiringDataModel>)
//}
