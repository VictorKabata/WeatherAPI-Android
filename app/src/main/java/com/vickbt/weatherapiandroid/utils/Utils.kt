package com.vickbt.weatherapiandroid.utils

import com.vickbt.shared.domain.utils.MeasurementOptions

fun Double.toTempUnitOfMeasurement(unitOfMeasurement: MeasurementOptions): String {
    val value = this.toInt()

    return if (unitOfMeasurement == MeasurementOptions.METRIC) {
        "$value°C"
    } else {
        "$value°F"
    }
}

fun Double.toSpeedUnitOfMeasurement(unitOfMeasurement: MeasurementOptions): String {
    val value = this.toInt()

    return if (unitOfMeasurement == MeasurementOptions.METRIC) {
        "$value km/h"
    } else {
        "$value m/h"
    }
}
