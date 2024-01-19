package com.vickbt.domain.models

import kotlinx.datetime.LocalDateTime

data class Forecastday(
    val dateEpoch: LocalDateTime,
    val day: DayForecast,
    val hour: List<HourForecast>
)
