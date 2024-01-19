package com.vickbt.domain.models

import kotlinx.datetime.LocalDateTime

data class ForecastWeather(
    val current: Current,
    val location: Location,
    val forecast:List<Forecastday>
)
