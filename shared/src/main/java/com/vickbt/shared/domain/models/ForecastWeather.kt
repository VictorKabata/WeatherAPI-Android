package com.vickbt.shared.domain.models

data class ForecastWeather(
    val current: Current,
    val location: Location,
    val forecast: List<ForecastDay>
)
