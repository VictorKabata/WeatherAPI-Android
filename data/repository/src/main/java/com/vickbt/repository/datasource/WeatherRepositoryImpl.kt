package com.vickbt.repository.datasource

import com.vickbt.shared.network.WeatherApiService
import com.vickbt.shared.network.utils.safeApiCall
import com.vickbt.repository.mappers.toDomain
import com.vickbt.repository.utils.LocationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days

class WeatherRepositoryImpl(
    private val weatherApiService: WeatherApiService,
    private val locationService: LocationService
) {

    private val timeZone = TimeZone.currentSystemDefault()

    suspend fun fetchForecastWeather(
        query: String? = null,
        language: String = "en"
    ): Flow<Result<com.vickbt.shared.domain.models.ForecastWeather>> {
        val location = locationService.requestLocationUpdates().first()

        return safeApiCall {
            weatherApiService.fetchForecastWeather(
                query = query ?: "${location?.latitude ?: 0.0},${location?.longitude ?: 0.0}",
                language = language
            ).toDomain()
        }
    }

    suspend fun fetchHistoryWeather(
        query: String? = null,
        language: String = "en",
        startDate: LocalDateTime = Clock.System.now().minus(14.days).toLocalDateTime(timeZone),
        endDate: LocalDateTime = Clock.System.now().toLocalDateTime(timeZone)
    ): Flow<Result<com.vickbt.shared.domain.models.HistoryForecast>> {
        return safeApiCall {
            val location = locationService.requestLocationUpdates().first()

            weatherApiService.fetchHistoryWeather(
                query = query ?: "${location?.latitude ?: 0.0},${location?.longitude ?: 0.0}",
                language = language,
                startDate = startDate,
                endDate = endDate
            ).toDomain()
        }
    }
}
