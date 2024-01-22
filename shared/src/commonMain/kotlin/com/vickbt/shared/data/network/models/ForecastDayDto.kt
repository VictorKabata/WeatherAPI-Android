package com.vickbt.shared.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayDto(
    @SerialName("date")
    val date: String,

    @SerialName("date_epoch")
    val dateEpoch: Int,

    @SerialName("day")
    val day: DayForecastDto,

    @SerialName("hour")
    val hour: List<HourForecastDto>
)
