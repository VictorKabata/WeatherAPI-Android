package com.vickbt.shared.domain.models

data class DayForecast(
    val avghumidity: Int,
    val avgtempC: Double,
    val avgtempF: Double,
    val avgvisKm: Double,
    val avgvisMiles: Double,
    val condition: Condition,
    val dailyChanceOfRain: Int,
    val dailyChanceOfSnow: Int,
    val dailyWillItRain: Int,
    val dailyWillItSnow: Int,
    val maxtempC: Double,
    val maxtempF: Double,
    val maxwindKph: Double,
    val maxwindMph: Double,
    val mintempC: Double,
    val mintempF: Double,
    val totalprecipIn: Double,
    val totalprecipMm: Double,
    val totalsnowCm: Double,
    val uv: Double
)
