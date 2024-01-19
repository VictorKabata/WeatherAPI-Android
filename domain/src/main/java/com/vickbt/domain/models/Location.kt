package com.vickbt.domain.models

data class Location(
    val country: String,
    val lat: Double,
    val lon: Double,
    val localtime: String,
    val name: String
)