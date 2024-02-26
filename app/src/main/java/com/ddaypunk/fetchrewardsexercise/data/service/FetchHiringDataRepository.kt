package com.ddaypunk.fetchrewardsexercise.data.service

import com.ddaypunk.fetchrewardsexercise.data.model.FetchHiringDataModel

class FetchHiringDataRepository {
    val client = KtorFetchHiringDataClient()
    suspend fun retrieve(): Map<String, List<FetchHiringDataModel>> {
        val data = client.retrieve()

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