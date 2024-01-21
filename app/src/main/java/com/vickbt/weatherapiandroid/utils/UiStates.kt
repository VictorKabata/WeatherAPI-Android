package com.vickbt.weatherapiandroid.utils

import com.vickbt.domain.models.ForecastWeather
import com.vickbt.domain.models.HistoryForecast

data class MainUiStates(
    val theme: Int = 0,
    val error: String? = null
)

data class HomeUiStates(
    val isLoading: Boolean = true,
    val error: String? = null,
    val forecastWeather: ForecastWeather? = null,
    val historyWeather: HistoryForecast? = null
)
