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
import com.vickbt.shared.domain.utils.MeasurementOptions
import konveyor.base.randomBuild
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertIs

class DtoToDomainTest {

    private val currentDto: CurrentDto = randomBuild()
    private val locationDto: LocationDto = randomBuild()
    private val conditionDto: ConditionDto = randomBuild()
    private val forecastWeatherDto: ForecastWeatherDto = randomBuild()
    private val forecastDayDto: ForecastDayDto = randomBuild()
    private val dayForecastDto: DayForecastDto = randomBuild()
    private val hourForecastDto: HourForecastDto = randomBuild()
    private val historyForecastDto: HistoryForecastDto = randomBuild()

    @Test
    fun `currentDto toDomain in metric maps to current correctly`() = runTest {
        val actualResult = currentDto.toDomain(unitOfMeasurement = MeasurementOptions.METRIC)

        assertIs<Current>(actualResult)
    }

    @Test
    fun `currentDto toDomain in imperial maps to current correctly`() = runTest {
        val actualResult = currentDto.toDomain(unitOfMeasurement = MeasurementOptions.IMPERIAL)

        assertIs<Current>(actualResult)
    }

    @Test
    fun `locationDto toDomain in metric maps to location correctly`() = runTest {
        val actualResult = locationDto.toDomain()

        assertIs<Location>(actualResult)
    }

    @Test
    fun `conditionDto toDomain maps to condition correctly`() = runTest {
        val actualResult = conditionDto.toDomain()

        assertIs<Condition>(actualResult)
    }

    @Test
    fun `forecastWeatherDto toDomain in metric maps to forecastWeather correctly`() = runTest {
        val actualResult =
            forecastWeatherDto.toDomain(unitOfMeasurement = MeasurementOptions.METRIC)

        assertIs<ForecastWeather>(actualResult)
    }

    @Test
    fun `forecastWeatherDto toDomain in imperial maps to forecastWeather correctly`() = runTest {
        val actualResult =
            forecastWeatherDto.toDomain(unitOfMeasurement = MeasurementOptions.IMPERIAL)

        assertIs<ForecastWeather>(actualResult)
    }

    @Test
    fun `forecastDayDto toDomain in metric maps to forecastDay correctly`() = runTest {
        val actualResult =
            forecastDayDto.toDomain(unitOfMeasurement = MeasurementOptions.METRIC)

        assertIs<ForecastDay>(actualResult)
    }

    @Test
    fun `forecastDayDto toDomain in imperial maps to forecastDay correctly`() = runTest {
        val actualResult =
            forecastDayDto.toDomain(unitOfMeasurement = MeasurementOptions.IMPERIAL)

        assertIs<ForecastDay>(actualResult)
    }

    @Test
    fun `dayForecastDto toDomain in metric maps to dayForecast correctly`() = runTest {
        val actualResult = dayForecastDto.toDomain(unitOfMeasurement = MeasurementOptions.METRIC)

        assertIs<DayForecast>(actualResult)
    }

    @Test
    fun `dayForecastDto toDomain in imperial maps to dayForecast correctly`() = runTest {
        val actualResult = dayForecastDto.toDomain(unitOfMeasurement = MeasurementOptions.IMPERIAL)

        assertIs<DayForecast>(actualResult)
    }

    @Test
    fun `hourForecastDto toDomain in metric maps to hourForecast correctly`() = runTest {
        val actualResult = hourForecastDto.toDomain(unitOfMeasurement = MeasurementOptions.METRIC)

        assertIs<HourForecast>(actualResult)
    }

    @Test
    fun `hourForecastDto toDomain in imperial maps to hourForecast correctly`() = runTest {
        val actualResult = hourForecastDto.toDomain(unitOfMeasurement = MeasurementOptions.IMPERIAL)

        assertIs<HourForecast>(actualResult)
    }

    @Test
    fun `historyForecastDto toDomain in metric maps to historyForecast correctly`() = runTest {
        val actualResult =
            historyForecastDto.toDomain(unitOfMeasurement = MeasurementOptions.METRIC)

        assertIs<HistoryForecast>(actualResult)
    }

    @Test
    fun `historyForecastDto toDomain in imperial maps to historyForecast correctly`() = runTest {
        val actualResult =
            historyForecastDto.toDomain(unitOfMeasurement = MeasurementOptions.IMPERIAL)

        assertIs<HistoryForecast>(actualResult)
    }
}
