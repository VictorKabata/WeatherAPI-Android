package com.vickbt.weatherapiandroid.utils

import com.vickbt.network.dtos.CurrentWeatherDto

data class HomeUiStates(
    val isLoading: Boolean = true,
    val error: String? = null,
    val currentWeather: CurrentWeatherDto? = null
)