package com.ddaypunk.fetchrewardsexercise.core.fake

import com.ddaypunk.fetchrewardsexercise.core.client.ApiClient
import com.ddaypunk.fetchrewardsexercise.hiring.data.model.HiringDataModel

class FakeApiClient : ApiClient {
    override suspend fun retrieve(): List<HiringDataModel> {
        return listOf(
            HiringDataModel(
                id = 1,
                listId = 1,
                name = ""
            ),
            HiringDataModel(
                id = 2,
                listId = 1,
                name = "Hello"
            ),
            HiringDataModel(
                id = 3,
                listId = 2,
                name = null
            ),
            HiringDataModel(
                id = 4,
                listId = 3,
                name = "there"
            ),
            HiringDataModel(
                id = 5,
                listId = 3,
                name = "Sup"
            ),
            HiringDataModel(
                id = 6,
                listId = 2,
                name = "Dude"
            ),
        )
    }
}