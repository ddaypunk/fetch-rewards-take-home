package com.ddaypunk.fetchrewardsexercise.hiring.presentation.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ddaypunk.fetchrewardsexercise.hiring.presentation.component.ListCardState

class MainScreenPreviewProvider : PreviewParameterProvider<List<ListCardState>> {
    override val values = sequenceOf(
        listOf(
            ListCardState(
                title = "Test 1",
                isExpanded = true,
                entries = listOf(
                    "Entry 1",
                    "Entry 10",
                    "Entry 100"
                ),
                onClick = {}
            ),
            ListCardState(
                title = "Test 2",
                isExpanded = true,
                entries = listOf(
                    "Entry 2",
                    "Entry 20",
                    "Entry 200"
                ),
                onClick = {}
            )
        )
    )
}
