package com.ddaypunk.fetchrewardsexercise.core.client

import com.ddaypunk.fetchrewardsexercise.hiring.data.model.HiringDataModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.io.IOException

class KtorApiClient(
    private val httpClient: HttpClient
) : ApiClient {
    override suspend fun retrieve(): List<HiringDataModel> {
        val result = try {
            httpClient.get<List<HiringDataModel>> {
                url("$BASE_URL/hiring.json")
                contentType(ContentType.Application.Json)
            }
        } catch (e: IOException) {
            throw DataClientException(DataClientError.SERVICE_UNAVAILABLE)
        }

        return result
    }

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com"
    }
}

enum class DataClientError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class DataClientException(error: DataClientError):
    Exception("An error occurred when getting data from client: $error")