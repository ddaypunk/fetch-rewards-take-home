package com.ddaypunk.fetchrewardsexercise.hiring.data.repository

import com.ddaypunk.fetchrewardsexercise.core.client.ApiClient
import com.ddaypunk.fetchrewardsexercise.core.client.ApiResponse
import com.ddaypunk.fetchrewardsexercise.core.client.DataClientException
import com.ddaypunk.fetchrewardsexercise.hiring.data.model.HiringDataModel
import javax.inject.Inject

class HiringDataRepository @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun retrieve(): ApiResponse<Map<String, List<HiringDataModel>>> {
        return try {
            val data = apiClient.retrieve()
            val response = data.filter { item ->
                !item.name.isNullOrBlank()
            }.sortedWith(
                compareBy({ it.listId }, { it.name })
            )
                .groupBy { item ->
                    item.listId.toString()
                }

            ApiResponse.Success(response)
        } catch (e: DataClientException) {
            ApiResponse.Error(e)
        }
    }
}

