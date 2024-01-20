package com.vickbt.network

import com.vickbt.network.dtos.CurrentWeatherDto
import com.vickbt.network.dtos.ForecastWeatherDto
import com.vickbt.network.dtos.HistoryForecastDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days

class WeatherApiService(private val weatherApiClient: HttpClient) {

    private val timeZone = TimeZone.currentSystemDefault()

    suspend fun fetchCurrentWeather(
        query: String,
        language: String
    ): CurrentWeatherDto {
        return weatherApiClient.get("current.json") {
            parameter("q", query)
            parameter("lang", language)
        }.body<CurrentWeatherDto>()
    }

    suspend fun fetchForecastWeather(
        query: String,
        language: String
    ): ForecastWeatherDto {
        return weatherApiClient.get("forecast.json") {
            parameter("q", query)
            parameter("lang", language)
            parameter("days", 7)
        }.body<ForecastWeatherDto>()
    }

    suspend fun fetchHistoryWeather(
        query: String,
        language: String,
        startDate: LocalDateTime = Clock.System.now().toLocalDateTime(timeZone),
        endDate: LocalDateTime = Clock.System.now().minus(14.days).toLocalDateTime(timeZone)
    ): HistoryForecastDto {
        return weatherApiClient.get("history.json") {
            parameter("q", query)
            parameter("lang", language)
            parameter("dt", "${startDate.year}-${startDate.month}-${startDate.date}")
            parameter("end_dt", "${endDate.year}-${endDate.month}-${endDate.date}")
        }.body<HistoryForecastDto>()
    }
}
