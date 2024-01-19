package com.vickbt.domain.models


data class Forecastday(
    val dateEpoch: Int,
    val day: DayForecast,
    val hour: List<HourForecast>
)
