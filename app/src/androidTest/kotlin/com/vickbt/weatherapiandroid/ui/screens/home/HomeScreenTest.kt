package com.vickbt.weatherapiandroid.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.vickbt.shared.data.repository.datasource.WeatherRepository
import com.vickbt.shared.domain.models.ApiError
import com.vickbt.weatherapiandroid.ui.theme.WeatherAPIAndroidTheme
import com.vickbt.weatherapiandroid.utils.HomeUiStates
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    private val weatherRepository = mockk<WeatherRepository>(relaxed = true)
    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        homeViewModel = spyk(HomeViewModel(weatherRepository = weatherRepository))
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun when_homeUiState_isLoading_is_true_the_progressBar_is_displayed() {
        homeViewModel._homeUiState.update { it.copy(isLoading = true) }

        composeTestRule.setContent {
            WeatherAPIAndroidTheme {
                HomeScreen(paddingValues = PaddingValues(), viewModel = homeViewModel)
            }
        }
        composeTestRule.onNodeWithTag("loading_progress_bar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("weather_info_column").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error_text").assertDoesNotExist()

    }

    @Test
    fun when_homeUiState_isLoading_is_false_the_progressBar_is_not_displayed() {
        homeViewModel._homeUiState.update { it.copy(isLoading = false) }

        composeTestRule.setContent {
            WeatherAPIAndroidTheme {
                HomeScreen(paddingValues = PaddingValues(), viewModel = homeViewModel)
            }
        }
        composeTestRule.onNodeWithTag("loading_progress_bar").assertDoesNotExist()
    }

    @Test
    fun when_homeUiState_error_is_not_null_error_text_is_displayed() {
        // Given
        coEvery { weatherRepository.fetchForecastWeather() } throws ApiError(
            code = 0, error = "Unknown error has occurred"
        )

        // When
        homeViewModel.fetchForecastWeather()

        composeTestRule.setContent {
            WeatherAPIAndroidTheme {
                HomeScreen(paddingValues = PaddingValues(), viewModel = homeViewModel)
            }
        }
        composeTestRule.onNodeWithTag("loading_progress_bar").assertDoesNotExist()
        composeTestRule.onNodeWithTag("weather_info_column").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error_text").assertIsDisplayed()
        composeTestRule.onNodeWithText("Unknown error has occurred").assertExists()

    }

}
