package com.ddaypunk.fetchrewardsexercise.core.client

sealed class ApiResponse<T>(
    val data: T?,
    val throwable: Throwable? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(throwable: Throwable) : ApiResponse<T>(null, throwable)
}