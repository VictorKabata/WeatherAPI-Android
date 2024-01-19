package com.vickbt.network.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("current")
    val current: CurrentDto,
    @SerialName("location")
    val location: LocationDto
)