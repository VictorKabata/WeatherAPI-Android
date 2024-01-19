package com.vickbt.repository.datasource

import com.vickbt.network.WeatherApiService
import com.vickbt.network.dtos.CurrentWeatherDto
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(private val weatherApiService: WeatherApiService) {

    suspend fun fetchCurrentWeather(
        query: String,
        language: String = "en"
    ): Flow<CurrentWeatherDto> {
        return weatherApiService.fetchCurrentWeather(query = query, language = language)
    }

}