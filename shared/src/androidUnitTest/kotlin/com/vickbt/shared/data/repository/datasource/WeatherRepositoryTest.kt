package com.vickbt.shared.data.repository.datasource

import com.google.android.gms.maps.model.LatLng
import com.russhwolf.settings.ObservableSettings
import com.vickbt.shared.data.network.MockWeatherApiClient
import com.vickbt.shared.data.network.WeatherApiService
import com.vickbt.shared.domain.models.ApiError
import com.vickbt.shared.domain.models.Location
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import utils.LocationService
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WeatherRepositoryTest {

    private val mockWeatherApiClient = MockWeatherApiClient()

    private lateinit var mockKtorHttpClient: HttpClient
    private lateinit var weatherApiService: WeatherApiService

    private lateinit var weatherRepository: WeatherRepository

    private val observableSettings = mockk<ObservableSettings>(relaxed = true)
    private val locationService = mockk<LocationService>(relaxed = true)

    private val forecastLocation = Location(
        country = "Kenya",
        lat = -1.28,
        lon = 36.82,
        localtime = Instant.fromEpochSeconds(1705925993)
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        name = "Nairobi"
    )

    private val historyLocation = Location(
        country = "Kenya",
        lat = -1.28,
        lon = 36.82,
        localtime = Instant.fromEpochSeconds(1705925879)
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        name = "Nairobi"
    )

    @BeforeTest
    fun setup() {
        coEvery { locationService.requestLocationUpdates().firstOrNull() } returns LatLng(
            -1.28,
            36.82
        )

        mockKtorHttpClient = mockWeatherApiClient.weatherApiClient

        weatherApiService = WeatherApiService(weatherApiClient = mockKtorHttpClient)

        weatherRepository = WeatherRepository(
            weatherApiService = weatherApiService,
            observableSettings = observableSettings,
            locationService = locationService
        )

        coEvery { weatherRepository.getMeasurementSettings().first() } returns 0
    }

    @AfterTest
    fun tearDown() {
        mockKtorHttpClient.close()
    }

    @Test
    fun `fetchForecastWeather returns success result on success`() = runTest {
        // When
        val result = weatherRepository.fetchForecastWeather()

        // Then
        assertTrue(result.first().isSuccess)
        assertEquals(expected = result.first().getOrNull()?.location, actual = forecastLocation)
    }

    @Test
    fun `fetchForecastWeather returns failure result on error`() = runTest {
        mockWeatherApiClient.throwError(
            httpStatus = HttpStatusCode.BadRequest,
            response = mockWeatherApiClient.clientErrorResponseJson
        )

        // Then
        assertFailsWith(ApiError::class, message = "Parameter 'q' not provided.") {
            weatherRepository.fetchForecastWeather()
        }
    }

    @Test
    fun `fetchHistoryWeather returns success result on success`() = runTest {
        val result = weatherRepository.fetchHistoryWeather()

        assertTrue(result.first().isSuccess)
        assertEquals(expected = result.first().getOrNull()?.location, actual = historyLocation)
    }

    @Test
    fun `fetchHistoryWeather returns success result on failure`() = runTest {
        mockWeatherApiClient.throwError(
            httpStatus = HttpStatusCode.BadRequest,
            response = mockWeatherApiClient.clientErrorResponseJson
        )

        assertFailsWith(ApiError::class, message = "Parameter 'q' not provided.") {
            weatherRepository.fetchHistoryWeather()
        }
    }
}
