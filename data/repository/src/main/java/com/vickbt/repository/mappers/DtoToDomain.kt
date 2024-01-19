package com.vickbt.repository.mappers

import com.vickbt.domain.models.Condition
import com.vickbt.domain.models.Current
import com.vickbt.domain.models.CurrentWeather
import com.vickbt.domain.models.Location
import com.vickbt.network.dtos.ConditionDto
import com.vickbt.network.dtos.CurrentDto
import com.vickbt.network.dtos.CurrentWeatherDto
import com.vickbt.network.dtos.LocationDto
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun CurrentWeatherDto.toDomain(): CurrentWeather {
    return CurrentWeather(current = this.current.toDomain(), location = this.location.toDomain())
}

fun CurrentDto.toDomain(): Current {
    return Current(
        condition = this.condition.toDomain(),
        feelslikeC = this.feelslikeC,
        feelslikeF = this.feelslikeF,
        humidity = this.humidity,
        isDay = this.isDay,
        lastUpdated = this.lastUpdated,
        precipIn = this.precipIn,
        precipMm = this.precipMm,
        pressureIn = this.pressureIn,
        pressureMb = this.pressureMb,
        tempC = this.tempC,
        tempF = this.tempF,
        uv = this.uv,
        windKph = this.windKph,
        windMph = this.windMph
    )
}

fun LocationDto.toDomain(): Location {
    return Location(
        country = this.country,
        lat = this.lat,
        lon = this.lon,
        localtime = Instant.fromEpochSeconds(this.localtimeEpoch.toLong())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        name = this.name
    )
}

fun ConditionDto.toDomain(): Condition {
    return Condition(icon = this.icon, text = this.text)
}
