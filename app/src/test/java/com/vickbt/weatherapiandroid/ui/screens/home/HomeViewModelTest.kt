package com.vickbt.weatherapiandroid.ui.screens.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.vickbt.shared.data.repository.datasource.WeatherRepository
import com.vickbt.shared.domain.models.ApiError
import com.vickbt.shared.domain.models.ForecastWeather
import com.vickbt.shared.domain.models.HistoryForecast
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var homeViewModel: HomeViewModel

    private val weatherRepository = mockk<WeatherRepository>(relaxed = true)
    private val forecastWeather = mockk<ForecastWeather>(relaxed = true)
    private val historyWeather = mockk<HistoryForecast>(relaxed = true)
    private val apiError = ApiError(code = 0, error = "Error occurred")

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(weatherRepository = weatherRepository)
    }

    @Test
    fun `forecast in homeUiState is updated when fetchForecastWeather is successful`() = runTest {
        // Given
        coEvery { weatherRepository.fetchForecastWeather() } returns flowOf(
            Result.success(forecastWeather)
        )

        // When
        homeViewModel.fetchForecastWeather()
        val result = homeViewModel.homeUiState.value

        // Then
        assertThat(result.forecastWeather).isEqualTo(forecastWeather)
        assertThat(result.historyWeather).isNull()
        assertThat(result.isLoading).isFalse()

    }

    @Test
    fun `forecast in homeUiState is not updated when fetchForecastWeather fails`() = runTest {
        // Given
        coEvery { weatherRepository.fetchForecastWeather() } throws apiError

        // When
        homeViewModel.fetchForecastWeather()
        val result = homeViewModel.homeUiState.value

        // Then
        assertThat(result.forecastWeather).isNull()
        assertThat(result.historyWeather).isNull()
        assertThat(result.error).isEqualTo("Error occurred")
        assertThat(result.isLoading).isFalse()

    }
}
