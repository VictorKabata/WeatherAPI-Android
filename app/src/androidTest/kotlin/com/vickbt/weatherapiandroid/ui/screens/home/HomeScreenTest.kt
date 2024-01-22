package com.vickbt.weatherapiandroid.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.vickbt.shared.data.repository.datasource.WeatherRepository
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

}
