package com.vickbt.network

import com.vickbt.network.dtos.CurrentWeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WeatherApiService(private val weatherApiClient: HttpClient) {

    suspend fun fetchCurrentWeather(
        query: String,
        language: String
    ): Flow<CurrentWeatherDto> {
        return flowOf(
            weatherApiClient.get("current.json") {
                parameter("q", query)
                parameter("lang", language)
            }.body<CurrentWeatherDto>()
        )
    }

}