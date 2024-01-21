package com.vickbt.shared.repository.datasource

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getIntFlow
import com.vickbt.shared.domain.utils.Constants.MEASUREMENT_KEY
import com.vickbt.shared.domain.utils.Constants.THEME_KEY
import com.vickbt.shared.domain.utils.MEASUREMENT_OPTIONS
import com.vickbt.shared.domain.utils.THEME_OPTIONS
import com.vickbt.shared.network.WeatherApiService
import com.vickbt.shared.network.utils.safeApiCall
import com.vickbt.shared.repository.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import utils.LocationService
import kotlin.time.Duration.Companion.days

@OptIn(ExperimentalSettingsApi::class)
class WeatherRepositoryImpl(
    private val weatherApiService: WeatherApiService,
    private val observableSettings: ObservableSettings,
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

    suspend fun saveSettings(key: String, selection: Int) {
        observableSettings.putInt(key = key, value = selection)
    }

    suspend fun getThemeSettings(): Flow<Int> {
        return observableSettings.getIntFlow(
            key = THEME_KEY,
            defaultValue = THEME_OPTIONS.LIGHT_THEME.ordinal
        )
    }

    suspend fun getMeasurementSettings(): Flow<Int> {
        return observableSettings.getIntFlow(
            key = MEASUREMENT_KEY,
            defaultValue = MEASUREMENT_OPTIONS.METRIC.ordinal
        )
    }
}
