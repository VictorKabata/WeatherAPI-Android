package com.vickbt.domain.models

data class HistoryForecast(
    val forecast: List<ForecastDay>,
    val location: Location
)
