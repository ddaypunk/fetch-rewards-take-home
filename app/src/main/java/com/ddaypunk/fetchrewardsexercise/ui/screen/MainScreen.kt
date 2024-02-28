package com.ddaypunk.fetchrewardsexercise.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddaypunk.fetchrewardsexercise.ui.component.ExpandableListCard
import com.ddaypunk.fetchrewardsexercise.ui.component.ListCardState

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = viewModel()
) {
    when(val state = viewModel.uiState.collectAsState().value) {
        UiState.Error -> TODO()
        UiState.Loading -> Loading()
        is UiState.Success -> MainScreenReady(state.cardStates)
    }
}

@Composable
fun MainScreenReady(cardStates: List<ListCardState>) {
    MaterialTheme {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp),
        ) {
            cardStates.forEach { cardState ->
                item {
                    ExpandableListCard(
                        state = cardState
                    )
                }
            }
        }
    }
}

@Composable
fun Loading() {
    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = colorScheme.surfaceTint,
                strokeWidth = 4.dp
            )
        }
    }
}