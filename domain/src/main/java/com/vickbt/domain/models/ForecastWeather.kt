package com.vickbt.domain.models

data class ForecastWeather(
    val current: Current,
    val location: Location,
    val forecast: List<ForecastDay>
)
