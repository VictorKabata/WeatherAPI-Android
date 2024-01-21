package com.vickbt.weatherapiandroid.utils

import com.vickbt.shared.domain.models.ForecastWeather
import com.vickbt.shared.domain.models.HistoryForecast

data class MainUiStates(
    val theme: Int = 0,
    val error: String? = null
)

data class HomeUiStates(
    val isLoading: Boolean = true,
    val error: String? = null,
    val forecastWeather: com.vickbt.shared.domain.models.ForecastWeather? = null,
    val historyWeather: com.vickbt.shared.domain.models.HistoryForecast? = null
)
