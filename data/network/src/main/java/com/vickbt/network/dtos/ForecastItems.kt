package com.vickbt.network.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastItems(
    @SerialName("forecastday")
    val forecastday: List<ForecastdayDto>
)
