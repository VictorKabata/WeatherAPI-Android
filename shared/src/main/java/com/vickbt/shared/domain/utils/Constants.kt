package com.vickbt.shared.domain.utils

object Constants {

    const val BASE_URL = "api.weatherapi.com"
    const val URL_PATH = "v1/"

    const val THEME_KEY = "theme_key"
    const val MEASUREMENT_KEY = "measurement_key"
}

enum class ThemeOptions {
    LIGHT_THEME, DARK_THEME
}

enum class MeasurementOptions {
    METRIC, IMPERIAL
}
