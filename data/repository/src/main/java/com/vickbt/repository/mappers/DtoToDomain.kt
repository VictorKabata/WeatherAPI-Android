package com.vickbt.repository.mappers

import com.vickbt.domain.models.Condition
import com.vickbt.domain.models.Current
import com.vickbt.domain.models.CurrentWeather
import com.vickbt.domain.models.DayForecast
import com.vickbt.domain.models.ForecastDay
import com.vickbt.domain.models.ForecastWeather
import com.vickbt.domain.models.HistoryForecast
import com.vickbt.domain.models.HourForecast
import com.vickbt.domain.models.Location
import com.vickbt.network.dtos.ConditionDto
import com.vickbt.network.dtos.CurrentDto
import com.vickbt.network.dtos.CurrentWeatherDto
import com.vickbt.network.dtos.DayForecastDto
import com.vickbt.network.dtos.ForecastDayDto
import com.vickbt.network.dtos.ForecastWeatherDto
import com.vickbt.network.dtos.HistoryForecastDto
import com.vickbt.network.dtos.HourForecastDto
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

fun ForecastWeatherDto.toDomain(): ForecastWeather {
    return ForecastWeather(
        current = this.current.toDomain(),
        location = this.location.toDomain(),
        forecast = this.forecast.forecastday.map { it.toDomain() }
    )
}

fun ForecastDayDto.toDomain(): ForecastDay {
    return ForecastDay(
        dateEpoch = Instant.fromEpochSeconds(this.dateEpoch.toLong())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        day = this.day.toDomain(),
        hour = this.hour.map { it.toDomain() }
    )
}

fun DayForecastDto.toDomain(): DayForecast {
    return DayForecast(
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

fun HourForecastDto.toDomain(): HourForecast {
    return HourForecast(
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

fun HistoryForecastDto.toDomain(): HistoryForecast {
    return HistoryForecast(
        forecast = this.forecast.forecastday.map { it.toDomain() },
        location = this.location.toDomain()
    )
}
