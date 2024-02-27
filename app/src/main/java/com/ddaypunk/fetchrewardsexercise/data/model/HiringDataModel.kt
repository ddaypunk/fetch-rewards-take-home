package com.ddaypunk.fetchrewardsexercise.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HiringDataModel(
    val id: Int,
    val listId: Int,
    val name: String?
)
