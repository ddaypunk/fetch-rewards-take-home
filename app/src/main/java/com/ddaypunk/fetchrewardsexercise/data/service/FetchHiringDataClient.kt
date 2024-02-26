package com.ddaypunk.fetchrewardsexercise.data.service

import com.ddaypunk.fetchrewardsexercise.data.model.FetchHiringDataModel

interface FetchHiringDataClient {
    suspend fun retrieve(): List<FetchHiringDataModel>
}