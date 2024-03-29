package com.vickbt.shared.data.network

import com.vickbt.shared.data.network.models.ForecastWeatherDto
import com.vickbt.shared.data.network.models.HistoryForecastDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.datetime.LocalDateTime

/**Weather API*/
class WeatherApiService(private val weatherApiClient: HttpClient) {

    /**Return weather forecast for the next 7 days/ 1 week*/
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

    /**Returns weather forecast for the past 2 weeks/ 14 days*/
    suspend fun fetchHistoryWeather(
        query: String,
        language: String,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): HistoryForecastDto {
        return weatherApiClient.get("history.json") {
            parameter("q", query)
            parameter("lang", language)
            parameter("dt", "${startDate.date}")
            parameter("end_dt", "${endDate.date}")
        }.body<HistoryForecastDto>()
    }
}
