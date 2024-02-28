package com.ddaypunk.fetchrewardsexercise.core.client

import com.ddaypunk.fetchrewardsexercise.hiring.data.model.HiringDataModel

interface ApiClient {
    suspend fun retrieve(): List<HiringDataModel>
}