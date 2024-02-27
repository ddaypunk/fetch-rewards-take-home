package com.ddaypunk.fetchrewardsexercise.ui.component

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ddaypunk.fetchrewardsexercise.R
import com.ddaypunk.fetchrewardsexercise.data.model.HiringDataModel
import com.ddaypunk.fetchrewardsexercise.ui.theme.Typography

@Immutable
data class ListCardState(
    val title: String,
    val entries: List<HiringDataModel>?
)

@Composable
fun ExpandableListCard(
    state: ListCardState
) {
    var isExpanded by remember { mutableStateOf(false) }
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
                IconToggleButton(checked = isExpanded, onCheckedChange = { isExpanded = !isExpanded }) {
                    when(isExpanded) {
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

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    state.entries?.forEach { model ->
                        Text(
                            text = "${model.name}",
                            style = Typography.bodyMedium,
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}