package com.vickbt.repository.mappers

import com.vickbt.shared.network.models.ConditionDto
import com.vickbt.shared.network.models.CurrentDto
import com.vickbt.shared.network.models.DayForecastDto
import com.vickbt.shared.network.models.ForecastDayDto
import com.vickbt.shared.network.models.ForecastWeatherDto
import com.vickbt.shared.network.models.HistoryForecastDto
import com.vickbt.shared.network.models.HourForecastDto
import com.vickbt.shared.network.models.LocationDto
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun CurrentDto.toDomain(): com.vickbt.shared.domain.models.Current {
    return com.vickbt.shared.domain.models.Current(
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

fun LocationDto.toDomain(): com.vickbt.shared.domain.models.Location {
    return com.vickbt.shared.domain.models.Location(
        country = this.country,
        lat = this.lat,
        lon = this.lon,
        localtime = Instant.fromEpochSeconds(this.localtimeEpoch.toLong())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        name = this.name
    )
}

fun ConditionDto.toDomain(): com.vickbt.shared.domain.models.Condition {
    return com.vickbt.shared.domain.models.Condition(icon = this.icon, text = this.text)
}

fun ForecastWeatherDto.toDomain(): com.vickbt.shared.domain.models.ForecastWeather {
    return com.vickbt.shared.domain.models.ForecastWeather(
        current = this.current.toDomain(),
        location = this.location.toDomain(),
        forecast = this.forecast.forecastday.map { it.toDomain() }
    )
}

fun ForecastDayDto.toDomain(): com.vickbt.shared.domain.models.ForecastDay {
    return com.vickbt.shared.domain.models.ForecastDay(
        dateEpoch = Instant.fromEpochSeconds(this.dateEpoch.toLong())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        day = this.day.toDomain(),
        hour = this.hour.map { it.toDomain() }
    )
}

fun DayForecastDto.toDomain(): com.vickbt.shared.domain.models.DayForecast {
    return com.vickbt.shared.domain.models.DayForecast(
        avghumidity = this.avghumidity,
        avgtempC = this.avgtempC,
        avgtempF = this.avgtempF,
        avgvisKm = this.avgvisKm,
        avgvisMiles = this.avgvisMiles,
        condition = this.condition.toDomain(),
        dailyChanceOfRain = this.dailyChanceOfRain,
        dailyChanceOfSnow = this.dailyChanceOfSnow,
        dailyWillItRain = this.dailyWillItRain,
        dailyWillItSnow = this.dailyWillItSnow,
        maxtempC = this.maxtempC,
        maxtempF = this.maxtempF,
        maxwindKph = this.maxwindKph,
        maxwindMph = this.maxwindMph,
        mintempC = this.mintempC,
        mintempF = this.mintempF,
        totalprecipIn = this.totalprecipIn,
        totalprecipMm = this.totalprecipMm,
        totalsnowCm = this.totalsnowCm,
        uv = this.uv
    )
}

fun HourForecastDto.toDomain(): com.vickbt.shared.domain.models.HourForecast {
    return com.vickbt.shared.domain.models.HourForecast(
        chanceOfRain = this.chanceOfRain,
        chanceOfSnow = this.chanceOfSnow,
        cloud = this.cloud,
        condition = this.condition.toDomain(),
        feelslikeC = this.feelslikeC,
        feelslikeF = this.feelslikeF,
        humidity = this.humidity,
        snowCm = this.snowCm,
        tempC = this.tempC,
        tempF = this.tempF,
        timeEpoch = Instant.fromEpochSeconds(this.timeEpoch.toLong())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        uv = this.uv,
        willItRain = this.willItRain,
        willItSnow = this.willItSnow,
        windKph = this.windKph,
        windMph = this.windMph
    )
}

fun HistoryForecastDto.toDomain(): com.vickbt.shared.domain.models.HistoryForecast {
    return com.vickbt.shared.domain.models.HistoryForecast(
        forecast = this.forecast.forecastday.map { it.toDomain() },
        location = this.location.toDomain()
    )
}
