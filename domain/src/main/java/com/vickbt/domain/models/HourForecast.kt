package com.vickbt.domain.models


data class HourForecast(
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val cloud: Int,
    val condition: Condition,
    val feelslikeC: Double,
    val feelslikeF: Double,
    val humidity: Int,
    val snowCm: Int,
    val tempC: Double,
    val tempF: Double,
    val timeEpoch: Int,
    val uv: Int,
    val willItRain: Int,
    val willItSnow: Int,
    val windKph: Double,
    val windMph: Double
)
