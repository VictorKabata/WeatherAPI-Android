package com.vickbt.weatherapiandroid.utils

import com.vickbt.domain.models.CurrentWeather

data class MainUiStates(
    val theme: Int = 0,
    val error: String? = null
)

data class HomeUiStates(
    val isLoading: Boolean = true,
    val error: String? = null,
    val currentWeather: CurrentWeather? = null
)
