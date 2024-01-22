package com.vickbt.shared.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryForecastDto(
    @SerialName("forecast")
    val forecast: ForecastDto,

    @SerialName("location")
    val location: LocationDto
)
