package com.ddaypunk.fetchrewardsexercise.data.service

import com.ddaypunk.fetchrewardsexercise.data.model.HiringDataModel
import javax.inject.Inject

class HiringDataRepository @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun retrieve(): Map<String, List<HiringDataModel>> {
        val data = apiClient.retrieve()

        // TODO: try sequence here?
        return data.filter { item ->
            !item.name.isNullOrBlank()
        }.sortedWith(
            compareBy({ it.listId }, { it.name })
        )
        .groupBy { item ->
            item.listId.toString()
        }
    }
}