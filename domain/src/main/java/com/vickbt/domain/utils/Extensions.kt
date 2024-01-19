package com.vickbt.domain.utils

import kotlinx.datetime.LocalDateTime

fun LocalDateTime.toReadableFormat(): String {
    return "${this.dayOfWeek}, ${this.dayOfMonth} ${this.month} $year".capitalizeEachWord()
}

fun String.capitalizeEachWord(): String {
    return lowercase().split(" ").joinToString(" ") { firstCharacter ->
        firstCharacter.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }
}
