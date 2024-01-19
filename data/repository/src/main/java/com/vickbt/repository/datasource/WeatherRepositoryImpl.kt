package com.vickbt.repository.datasource

import com.vickbt.domain.models.CurrentWeather
import com.vickbt.network.WeatherApiService
import com.vickbt.network.utils.safeApiCall
import com.vickbt.repository.mappers.toDomain
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(private val weatherApiService: WeatherApiService) {

    suspend fun fetchCurrentWeather(
        query: String,
        language: String = "en"
    ): Flow<Result<CurrentWeather>> {
        return safeApiCall {
            weatherApiService.fetchCurrentWeather(query = query, language = language).toDomain()
        }
    }

}