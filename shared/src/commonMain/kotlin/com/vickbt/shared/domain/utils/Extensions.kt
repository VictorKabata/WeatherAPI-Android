package com.vickbt.shared.domain.utils

import kotlinx.datetime.LocalDateTime

/**Format LocalDate time to human readable format ie. Monday, 01 January 1990*/
fun LocalDateTime.toReadableFormat(): String {
    return "${this.dayOfWeek}, ${this.dayOfMonth} ${this.month} $year".capitalizeEachWord()
}

/**Capitalize each first letter of string ie. BIG brown is formatted to Big Brown*/
fun String.capitalizeEachWord(): String {
    return lowercase().split(" ").joinToString(" ") { firstCharacter ->
        firstCharacter.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }
}

/**Appends degree celsius or degree fahrenheit depending on the [MeasurementOptions] passed*/
fun Double.toTempUnitOfMeasurement(unitOfMeasurement: MeasurementOptions): String {
    val value = this.toInt()

    return if (unitOfMeasurement == MeasurementOptions.METRIC) {
        "$value°C"
    } else {
        "$value°F"
    }
}

/**Appends kilometer per hour or miles per hour depending on [MeasurementOptions] passed*/
fun Double.toSpeedUnitOfMeasurement(unitOfMeasurement: MeasurementOptions): String {
    val value = this.toInt()

    return if (unitOfMeasurement == MeasurementOptions.METRIC) {
        "$value km/h"
    } else {
        "$value m/h"
    }
}

/**Appends [https:] to condition icon image url*/
fun String.toImageFormat(): String {
    return "https:$this"
}
