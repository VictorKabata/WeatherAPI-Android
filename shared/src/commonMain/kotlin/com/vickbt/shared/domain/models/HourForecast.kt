package com.vickbt.shared.domain.models

import kotlinx.datetime.LocalDateTime

data class HourForecast(
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val cloud: Int,
    val condition: Condition,
    val feelslikeC: Double,
    val feelslikeF: Double,
    val feelslike: Double,
    val humidity: Int,
    val snowCm: Double,
    val tempC: Double,
    val tempF: Double,
    val temp: Double,
    val timeEpoch: LocalDateTime,
    val uv: Double,
    val willItRain: Int,
    val willItSnow: Int,
    val windKph: Double,
    val windMph: Double,
    val wind: Double
)
