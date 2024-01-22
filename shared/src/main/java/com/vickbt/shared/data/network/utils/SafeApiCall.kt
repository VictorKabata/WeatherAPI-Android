package com.vickbt.shared.data.network.utils

import com.vickbt.shared.data.network.models.ApiErrorDto
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

suspend fun <T : Any?> safeApiCall(apiCall: suspend () -> T): Flow<Result<T>> {
    return flowOf(
        try {
            Result.success(apiCall.invoke())
        } catch (e: RedirectResponseException) {
            val error = parseNetworkError(e.response.body())
            Result.failure(exception = error)
        } catch (e: ClientRequestException) {
            val error = parseNetworkError(e.response.body())
            Result.failure(exception = error)
        } catch (e: UnresolvedAddressException) {
            val error = parseNetworkError(exception = e)
            Result.failure(exception = error)
        } catch (e: Exception) {
            val error = parseNetworkError(exception = e)
            Result.failure(exception = error)
        }
    )
}

internal suspend fun parseNetworkError(
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): Exception {
    val error = errorResponse?.body<ApiErrorDto>()

    throw com.vickbt.shared.domain.models.ApiError(
        code = error?.code ?: 0,
        error = error?.message ?: exception?.message ?: "Unknown error occurred"
    )
}
