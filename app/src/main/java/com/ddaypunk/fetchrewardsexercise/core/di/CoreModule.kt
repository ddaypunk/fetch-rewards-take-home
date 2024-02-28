package com.ddaypunk.fetchrewardsexercise.core.di

import com.ddaypunk.fetchrewardsexercise.core.client.ApiClient
import com.ddaypunk.fetchrewardsexercise.core.client.KtorApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    @Singleton
    @Provides
    fun provideApiClient(httpClient: HttpClient): ApiClient = KtorApiClient(httpClient)
}