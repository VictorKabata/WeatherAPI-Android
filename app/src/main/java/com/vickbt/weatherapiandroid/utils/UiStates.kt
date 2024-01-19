package com.vickbt.weatherapiandroid.utils

import com.vickbt.domain.models.ForecastWeather

data class MainUiStates(
    val theme: Int = 0,
    val error: String? = null
)

data class HomeUiStates(
    val isLoading: Boolean = true,
    val error: String? = null,
    val forecastWeather: ForecastWeather? = null
)
