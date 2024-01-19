package com.vickbt.network.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastItemsDto(
    @SerialName("forecastday")
    val forecastday: List<ForecastdayDto>
)
