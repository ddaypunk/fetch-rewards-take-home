package com.ddaypunk.fetchrewardsexercise.data.service

import com.ddaypunk.fetchrewardsexercise.data.model.HiringDataModel

interface ApiClient {
    suspend fun retrieve(): List<HiringDataModel>
}