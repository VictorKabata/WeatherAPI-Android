package com.vickbt.weatherapiandroid.utils

import com.vickbt.shared.domain.models.ForecastWeather
import com.vickbt.shared.domain.models.HistoryForecast
import com.vickbt.shared.domain.utils.MeasurementOptions
import com.vickbt.shared.domain.utils.ThemeOptions

data class MainUiStates(
    val theme: ThemeOptions? = null,
    val unitOfMeasurement: MeasurementOptions? = null,
    val error: String? = null
)

data class HomeUiStates(
    val isLoading: Boolean = true,
    val error: String? = null,
    val forecastWeather: ForecastWeather? = null,
    val historyWeather: HistoryForecast? = null,
    val unitOfMeasurement: MeasurementOptions = MeasurementOptions.METRIC
)
