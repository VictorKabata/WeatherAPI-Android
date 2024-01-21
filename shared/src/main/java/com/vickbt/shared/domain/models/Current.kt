package com.vickbt.shared.domain.models

data class Current(
    val condition: Condition,
    val feelslikeC: Double,
    val feelslikeF: Double,
    val feelslike: Double,
    val humidity: Int,
    val isDay: Int,
    val lastUpdated: String,
    val precipIn: Double,
    val precipMm: Double,
    val pressureIn: Double,
    val pressureMb: Double,
    val tempC: Double,
    val tempF: Double,
    val temp: Double,
    val uv: Double,
    val windKph: Double,
    val windMph: Double,
    val wind: Double
)
