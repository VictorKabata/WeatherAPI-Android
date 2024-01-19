package com.vickbt.network

import com.vickbt.network.dtos.CurrentWeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiService(private val weatherApiClient: HttpClient) {

    suspend fun fetchCurrentWeather(
        query: String,
        language: String
    ): CurrentWeatherDto {
        return weatherApiClient.get("current.json") {
            parameter("q", query)
            parameter("lang", language)
        }.body<CurrentWeatherDto>()
    }
}
