package com.vickbt.network.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    @SerialName("current")
    val current: CurrentDto,

    @SerialName("forecast")
    val forecast: ForecastItems,

    @SerialName("location")
    val location: LocationDto
)
