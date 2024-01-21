package com.vickbt.shared.data.repository.mappers

import com.vickbt.shared.data.network.models.ConditionDto
import com.vickbt.shared.data.network.models.CurrentDto
import com.vickbt.shared.data.network.models.DayForecastDto
import com.vickbt.shared.data.network.models.ForecastDayDto
import com.vickbt.shared.data.network.models.ForecastWeatherDto
import com.vickbt.shared.data.network.models.HistoryForecastDto
import com.vickbt.shared.data.network.models.HourForecastDto
import com.vickbt.shared.data.network.models.LocationDto
import com.vickbt.shared.domain.models.Condition
import com.vickbt.shared.domain.models.Current
import com.vickbt.shared.domain.models.DayForecast
import com.vickbt.shared.domain.models.ForecastDay
import com.vickbt.shared.domain.models.ForecastWeather
import com.vickbt.shared.domain.models.HistoryForecast
import com.vickbt.shared.domain.models.HourForecast
import com.vickbt.shared.domain.models.Location
import com.vickbt.shared.domain.utils.MEASUREMENT_OPTIONS
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun CurrentDto.toDomain(unitOfMeasurement: MEASUREMENT_OPTIONS): Current {
    return Current(
        condition = this.condition.toDomain(),
        feelslikeC = this.feelslikeC,
        feelslikeF = this.feelslikeF,
        feelslike = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) feelslikeC else feelslikeF,
        humidity = this.humidity,
        isDay = this.isDay,
        lastUpdated = this.lastUpdated,
        precipIn = this.precipIn,
        precipMm = this.precipMm,
        pressureIn = this.pressureIn,
        pressureMb = this.pressureMb,
        tempC = this.tempC,
        tempF = this.tempF,
        temp = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) tempC else tempF,
        uv = this.uv,
        windKph = this.windKph,
        windMph = this.windMph,
        wind = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) windKph else windMph
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

fun ForecastWeatherDto.toDomain(unitOfMeasurement: MEASUREMENT_OPTIONS): ForecastWeather {
    return ForecastWeather(
        current = this.current.toDomain(unitOfMeasurement = unitOfMeasurement),
        location = this.location.toDomain(),
        forecast = this.forecast.forecastday.map { it.toDomain(unitOfMeasurement = unitOfMeasurement) }
    )
}

fun ForecastDayDto.toDomain(unitOfMeasurement: MEASUREMENT_OPTIONS): ForecastDay {
    return ForecastDay(
        dateEpoch = Instant.fromEpochSeconds(this.dateEpoch.toLong())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        day = this.day.toDomain(unitOfMeasurement = unitOfMeasurement),
        hour = this.hour.map { it.toDomain(unitOfMeasurement = unitOfMeasurement) }
    )
}

fun DayForecastDto.toDomain(unitOfMeasurement: MEASUREMENT_OPTIONS): DayForecast {
    return DayForecast(
        avghumidity = this.avghumidity,
        avgtempC = this.avgtempC,
        avgtempF = this.avgtempF,
        avgTemp = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) avgtempC else avgtempF,
        avgvisKm = this.avgvisKm,
        avgvisMiles = this.avgvisMiles,
        avgvis = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) avgvisKm else avgvisMiles,
        condition = this.condition.toDomain(),
        dailyChanceOfRain = this.dailyChanceOfRain,
        dailyChanceOfSnow = this.dailyChanceOfSnow,
        dailyWillItRain = this.dailyWillItRain,
        dailyWillItSnow = this.dailyWillItSnow,
        maxtempC = this.maxtempC,
        maxtempF = this.maxtempF,
        maxtemp = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) maxtempC else maxtempF,
        maxwindKph = this.maxwindKph,
        maxwindMph = this.maxwindMph,
        maxwind = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) maxwindKph else maxwindMph,
        mintempC = this.mintempC,
        mintempF = this.mintempF,
        mintemp = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) mintempC else mintempF,
        totalprecipIn = this.totalprecipIn,
        totalprecipMm = this.totalprecipMm,
        totalsnowCm = this.totalsnowCm,
        uv = this.uv
    )
}

fun HourForecastDto.toDomain(unitOfMeasurement: MEASUREMENT_OPTIONS): HourForecast {
    return HourForecast(
        chanceOfRain = this.chanceOfRain,
        chanceOfSnow = this.chanceOfSnow,
        cloud = this.cloud,
        condition = this.condition.toDomain(),
        feelslikeC = this.feelslikeC,
        feelslikeF = this.feelslikeF,
        feelslike = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) feelslikeC else feelslikeF,
        humidity = this.humidity,
        snowCm = this.snowCm,
        tempC = this.tempC,
        tempF = this.tempF,
        temp = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) tempC else tempF,
        timeEpoch = Instant.fromEpochSeconds(this.timeEpoch.toLong())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        uv = this.uv,
        willItRain = this.willItRain,
        willItSnow = this.willItSnow,
        windKph = this.windKph,
        windMph = this.windMph,
        wind = if (unitOfMeasurement == MEASUREMENT_OPTIONS.METRIC) windKph else windMph
    )
}

fun HistoryForecastDto.toDomain(unitOfMeasurement: MEASUREMENT_OPTIONS): HistoryForecast {
    return HistoryForecast(
        forecast = this.forecast.forecastday.map { it.toDomain(unitOfMeasurement = unitOfMeasurement) },
        location = this.location.toDomain()
    )
}
