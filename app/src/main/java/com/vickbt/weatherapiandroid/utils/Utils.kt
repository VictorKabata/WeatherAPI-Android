package com.vickbt.weatherapiandroid.utils

import com.vickbt.shared.domain.utils.MEASUREMENT_OPTIONS

fun Double.toTempUnitOfMeasurement(unitOfMeasurement: MEASUREMENT_OPTIONS): String {
    val value = this.toInt()

    return if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) "$value°C"
    else "$value°F"
}

fun Double.toSpeedUnitOfMeasurement(unitOfMeasurement: MEASUREMENT_OPTIONS): String {
    val value = this.toInt()

    return if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) "$value km/h"
    else "$value m/h"
}
