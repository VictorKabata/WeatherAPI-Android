package com.vickbt.weatherapiandroid.utils

import com.vickbt.shared.domain.utils.MEASUREMENT_OPTIONS
import com.vickbt.shared.domain.utils.THEME_OPTIONS

data class MainUiStates(
    val theme: THEME_OPTIONS? = null,
    val unitOfMeasurement: MEASUREMENT_OPTIONS? = null,
    val error: String? = null
)

data class HomeUiStates(
    val isLoading: Boolean = true,
    val error: String? = null,
    val forecastWeather: com.vickbt.shared.domain.models.ForecastWeather? = null,
    val historyWeather: com.vickbt.shared.domain.models.HistoryForecast? = null,
    val unitOfMeasurement: MEASUREMENT_OPTIONS = MEASUREMENT_OPTIONS.METRIC
)
