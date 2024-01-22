package com.vickbt.shared.data.network.utils

import com.vickbt.shared.data.network.models.ApiErrorDto
import com.vickbt.shared.domain.models.ApiError
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**Helper class to handle network requests success and failure states.*/
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

/**Parse network error response into exception with custom message*/
internal suspend fun parseNetworkError(
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): Exception {
    val error = errorResponse?.body<ApiErrorDto>()

    throw ApiError(
        code = error?.code ?: 0,
        error = error?.message ?: exception?.message ?: "Unknown error occurred"
    )
}
