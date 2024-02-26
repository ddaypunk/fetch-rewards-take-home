package com.ddaypunk.fetchrewardsexercise.data.service

import com.ddaypunk.fetchrewardsexercise.data.model.FetchHiringDataModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import java.io.IOException

class KtorFetchHiringDataClient(
//    private val httpClient: HttpClient
) : FetchHiringDataClient {
    private val httpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun retrieve(): List<FetchHiringDataModel> {
        val result = try {
            httpClient.get<List<FetchHiringDataModel>> {
                url("$BASE_URL/hiring.json")
                contentType(ContentType.Application.Json)
            }
        } catch (e: IOException) {
            throw DataClientException(DataClientError.SERVICE_UNAVAILABLE)
        }

//        when(result) {
//            in 200..299 -> Unit
//            500 -> throw DataClientException(DataClientError.SERVER_ERROR)
//            in 400..499 -> throw DataClientException(DataClientError.CLIENT_ERROR)
//            else -> throw DataClientException(DataClientError.UNKNOWN_ERROR)
//        }

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