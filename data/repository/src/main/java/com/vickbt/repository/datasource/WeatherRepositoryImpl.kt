package com.vickbt.repository.datasource

import com.vickbt.domain.models.CurrentWeather
import com.vickbt.domain.models.ForecastWeather
import com.vickbt.domain.models.HistoryForecast
import com.vickbt.network.WeatherApiService
import com.vickbt.network.utils.safeApiCall
import com.vickbt.repository.mappers.toDomain
import com.vickbt.repository.utils.LocationService
import io.github.aakira.napier.Napier
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

    suspend fun fetchCurrentWeather(
        query: String,
        language: String = "en"
    ): Flow<Result<CurrentWeather>> {
        return safeApiCall {
            weatherApiService.fetchCurrentWeather(query = query, language = language).toDomain()
        }
    }

    suspend fun fetchForecastWeather(
        query: String,
        language: String = "en"
    ): Flow<Result<ForecastWeather>> {
        val location = locationService.requestLocationUpdates().first()

        return safeApiCall {
            weatherApiService.fetchForecastWeather(
                query = "${location?.latitude ?: 0.0},${location?.longitude ?: 0.0}",
                language = language
            ).toDomain()
        }
    }

    suspend fun fetchHistoryWeather(
        query: String,
        language: String = "en",
        startDate: LocalDateTime = Clock.System.now().minus(14.days).toLocalDateTime(timeZone),
        endDate: LocalDateTime = Clock.System.now().toLocalDateTime(timeZone)
    ): Flow<Result<HistoryForecast>> {
        return safeApiCall {
            val location = locationService.requestLocationUpdates().first()

            weatherApiService.fetchHistoryWeather(
                query = "${location?.latitude ?: 0.0},${location?.longitude ?: 0.0}",
                language = language,
                startDate = startDate,
                endDate = endDate
            ).toDomain()
        }

    }
}
