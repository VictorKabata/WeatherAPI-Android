package com.vickbt.network.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherDto(
    @SerialName("current") val current: CurrentDto,
    @SerialName("forecast") val forecast: ForecastDto,
    @SerialName("location") val location: LocationDto
)
