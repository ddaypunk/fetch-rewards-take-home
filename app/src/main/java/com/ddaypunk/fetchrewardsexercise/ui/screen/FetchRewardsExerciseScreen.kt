package com.ddaypunk.fetchrewardsexercise.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddaypunk.fetchrewardsexercise.ui.component.ExpandableListCard
import com.ddaypunk.fetchrewardsexercise.ui.component.ListCardState

@Composable
fun FetchRewardsExerciseScreen(
    viewModel: FetchRewardsExerciseViewModel = viewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    MaterialTheme {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp),
        ) {
            state.data.keys.forEach { listId ->
                item {
                    ExpandableListCard(
                        state = ListCardState(
                            title = listId,
                            entries = state.data[listId]
                        )
                    )
                }
            }
        }
    }
}