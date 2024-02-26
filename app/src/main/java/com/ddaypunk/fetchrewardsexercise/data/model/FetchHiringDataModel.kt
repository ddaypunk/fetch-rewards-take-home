package com.ddaypunk.fetchrewardsexercise.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FetchHiringDataModel(
    val id: Int,
    val listId: Int,
    val name: String?
)
