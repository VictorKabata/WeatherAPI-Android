package com.vickbt.shared.domain.models

data class HistoryForecast(
    val forecast: List<ForecastDay>,
    val location: Location
)
