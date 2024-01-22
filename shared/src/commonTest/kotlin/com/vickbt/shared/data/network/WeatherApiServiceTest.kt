package com.vickbt.shared.data.network

import io.ktor.client.HttpClient
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days

/*class WeatherApiServiceTest {

    private val mockWeatherApiClient = MockWeatherApiClient()

    private lateinit var mockKtorHttpClient: HttpClient
    private lateinit var weatherApiService: WeatherApiService

    private lateinit var currentTime: Instant

    @BeforeTest
    fun setup() {
        mockKtorHttpClient = mockWeatherApiClient.weatherApiClient

        weatherApiService = WeatherApiService(weatherApiClient = mockKtorHttpClient)

        currentTime = Clock.System.now()

    }

    @AfterTest
    fun tearDown() {
        mockKtorHttpClient.close()
    }

    @Test
    fun `fetchForeCastWeather success returns ForecastWeatherDto`() = runTest {
        // when
        val actualResult = weatherApiService.fetchForecastWeather(query = "", language = "en")

        // then
        assertEquals(expected = ForecastLocation, actual = actualResult.location)
    }

    @Test
    fun `fetchHistoryWeather success returns HistoryForecastDto`() = runTest {
        // when
        val actualResult = weatherApiService.fetchHistoryWeather(
            query = "",
            language = "",
            startDate = currentTime.minus(10.days).toLocalDateTime(TimeZone.currentSystemDefault()),
            endDate = currentTime.toLocalDateTime(TimeZone.currentSystemDefault())
        )

        //then
        assertEquals(expected = HistoryLocation, actual = actualResult.location)
    }
}*/
