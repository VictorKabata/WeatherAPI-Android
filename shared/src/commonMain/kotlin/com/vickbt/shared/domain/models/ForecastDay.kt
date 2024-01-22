package com.vickbt.shared.domain.models

import kotlinx.datetime.LocalDateTime

data class ForecastDay(
    val dateEpoch: LocalDateTime,
    val day: DayForecast,
    val hour: List<HourForecast>
)
