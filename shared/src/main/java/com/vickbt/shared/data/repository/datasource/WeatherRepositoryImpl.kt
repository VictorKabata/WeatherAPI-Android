package com.vickbt.shared.data.repository.datasource

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getIntFlow
import com.vickbt.shared.data.network.WeatherApiService
import com.vickbt.shared.data.network.utils.safeApiCall
import com.vickbt.shared.data.repository.mappers.toDomain
import com.vickbt.shared.domain.utils.Constants.MEASUREMENT_KEY
import com.vickbt.shared.domain.utils.Constants.THEME_KEY
import com.vickbt.shared.domain.utils.MeasurementOptions
import com.vickbt.shared.domain.utils.ThemeOptions
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

    /**Return weather forecast for the next 7 days/ 1 week and maps the network response to domain classes*/
    suspend fun fetchForecastWeather(
        query: String? = null,
        language: String = "en"
    ): Flow<Result<com.vickbt.shared.domain.models.ForecastWeather>> {
        val location = locationService.requestLocationUpdates().first()
        val unitOfMeasurement = MeasurementOptions.entries[getMeasurementSettings().first()]

        return safeApiCall {
            weatherApiService.fetchForecastWeather(
                query = query ?: "${location?.latitude ?: 0.0},${location?.longitude ?: 0.0}",
                language = language
            ).toDomain(unitOfMeasurement = unitOfMeasurement)
        }
    }

    /**Returns weather forecast for the past 2 weeks/ 14 days and maps the network response to domain class*/
    suspend fun fetchHistoryWeather(
        query: String? = null,
        language: String = "en",
        startDate: LocalDateTime = Clock.System.now().minus(14.days).toLocalDateTime(timeZone),
        endDate: LocalDateTime = Clock.System.now().toLocalDateTime(timeZone)
    ): Flow<Result<com.vickbt.shared.domain.models.HistoryForecast>> {
        return safeApiCall {
            val location = locationService.requestLocationUpdates().first()
            val unitOfMeasurement = MeasurementOptions.entries[getMeasurementSettings().first()]

            weatherApiService.fetchHistoryWeather(
                query = query ?: "${location?.latitude ?: 0.0},${location?.longitude ?: 0.0}",
                language = language,
                startDate = startDate,
                endDate = endDate
            ).toDomain(unitOfMeasurement = unitOfMeasurement)
        }
    }

    /**Save setting option to key-value cache*/
    suspend fun saveSettings(key: String, selection: Int) {
        observableSettings.putInt(key = key, value = selection)
    }

    /**Get theme preference from key-value cache*/
    suspend fun getThemeSettings(): Flow<Int> {
        return observableSettings.getIntFlow(
            key = THEME_KEY,
            defaultValue = ThemeOptions.LIGHT_THEME.ordinal
        )
    }

    /**Get unit of measurement preference from key-value cache*/
    suspend fun getMeasurementSettings(): Flow<Int> {
        return observableSettings.getIntFlow(
            key = MEASUREMENT_KEY,
            defaultValue = MeasurementOptions.METRIC.ordinal
        )
    }
}
