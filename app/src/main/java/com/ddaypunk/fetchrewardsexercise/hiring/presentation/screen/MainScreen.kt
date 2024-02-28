package com.ddaypunk.fetchrewardsexercise.hiring.presentation.screen

import ErrorScreen
import LoadingScreen
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddaypunk.fetchrewardsexercise.hiring.presentation.component.ExpandableListCard
import com.ddaypunk.fetchrewardsexercise.hiring.presentation.component.ListCardState
import com.ddaypunk.fetchrewardsexercise.ui.theme.FetchRewardsExerciseTheme

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
            UiState.Loading -> LoadingScreen()
            is UiState.Ready -> MainScreenReady(state.cardStates)
            is UiState.Error -> ErrorScreen(state.error)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenReady(
    @PreviewParameter(MainScreenPreviewProvider::class)
    cardStates: List<ListCardState>
) {
    FetchRewardsExerciseTheme {
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
