package com.ddaypunk.fetchrewardsexercise.hiring.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ddaypunk.fetchrewardsexercise.R
import com.ddaypunk.fetchrewardsexercise.ui.theme.Typography

@Immutable
data class ListCardState(
    val title: String,
    val isExpanded: Boolean,
    val entries: List<String>?,
    val onClick: () -> Unit
)

@Composable
fun ExpandableListCard(
    state: ListCardState
) {
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "ListId = ${state.title}",
                    style = Typography.titleLarge,
                    textAlign = TextAlign.Center,
                )
                IconToggleButton(
                    checked = state.isExpanded,
                    onCheckedChange = { state.onClick.invoke() }) {
                    when (state.isExpanded) {
                        false -> Icon(
                            painterResource(id = R.drawable.ic_expand_more),
                            contentDescription = "Show list of names"
                        )

                        true -> Icon(
                            painterResource(id = R.drawable.ic_expand_less),
                            contentDescription = "Hide list of names"
                        )
                    }
                }

            }

            AnimatedVisibility(visible = state.isExpanded) {
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    state.entries?.forEach { name ->
                        Text(
                            text = name,
                            style = Typography.bodyMedium,
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}