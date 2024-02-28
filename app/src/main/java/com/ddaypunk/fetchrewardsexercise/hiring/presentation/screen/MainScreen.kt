package com.ddaypunk.fetchrewardsexercise.hiring.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddaypunk.fetchrewardsexercise.hiring.presentation.component.ExpandableListCard
import com.ddaypunk.fetchrewardsexercise.hiring.presentation.component.ListCardState

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = viewModel()
) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorScheme.background
    ) {
        when (val state = viewModel.uiState.collectAsState().value) {
            UiState.Error -> TODO()
            UiState.Loading -> Loading()
            is UiState.Ready -> MainScreenReady(state.cardStates)
        }
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