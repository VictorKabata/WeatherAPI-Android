package com.vickbt.weatherapiandroid.ui.activities.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.vickbt.shared.data.repository.datasource.WeatherRepository
import com.vickbt.shared.domain.utils.ThemeOptions
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
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var mainViewModel: MainViewModel

    private val weatherRepository = mockk<WeatherRepository>(relaxed = true)

    @Before
    fun setup() {
        mainViewModel = MainViewModel(weatherRepository = weatherRepository)
    }

    @Test
    fun getThemePreference_updates_light_theme_in_homeUiState()= runTest{
        coEvery { weatherRepository.getThemeSettings() } returns flowOf(0)

        mainViewModel.getThemePreference()

        assertThat(mainViewModel.mainUiState.value.theme).isEqualTo(ThemeOptions.LIGHT_THEME)
    }

    @Test
    fun getThemePreference_updates_dark_theme_in_homeUiState()= runTest{
        coEvery { weatherRepository.getThemeSettings() } returns flowOf(1)

        mainViewModel.getThemePreference()

        assertThat(mainViewModel.mainUiState.value.theme).isEqualTo(ThemeOptions.DARK_THEME)
    }

}
