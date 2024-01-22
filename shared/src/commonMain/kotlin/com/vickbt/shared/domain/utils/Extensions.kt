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
