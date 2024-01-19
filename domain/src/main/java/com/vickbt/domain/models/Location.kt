package com.vickbt.domain.models

import kotlinx.datetime.LocalDateTime

data class Location(
    val country: String,
    val lat: Double,
    val lon: Double,
    val localtime: LocalDateTime,
    val name: String
)
