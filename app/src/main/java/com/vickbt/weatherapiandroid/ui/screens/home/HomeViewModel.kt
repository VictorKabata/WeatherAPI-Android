package com.vickbt.weatherapiandroid.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.shared.data.repository.datasource.WeatherRepositoryImpl
import com.vickbt.shared.domain.utils.MeasurementOptions
import com.vickbt.weatherapiandroid.utils.HomeUiStates
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepositoryImpl) :
    ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiStates(isLoading = true))
    val homeUiState = _homeUiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _homeUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        fetchForecastWeather()
        fetchHistoryWeather()
        fetchUnitOfMeasurement()
    }

    fun fetchForecastWeather(query: String? = null) =
        viewModelScope.launch(coroutineExceptionHandler) {
            weatherRepository.fetchForecastWeather(query = query).collect { result ->
                result.onSuccess { forecastWeather ->
                    _homeUiState.update {
                        it.copy(isLoading = false, forecastWeather = forecastWeather)
                    }
                }.onFailure {
                    _homeUiState.update { it.copy(isLoading = false, error = it.error) }
                }
            }
        }

    fun fetchHistoryWeather(query: String? = null) =
        viewModelScope.launch(coroutineExceptionHandler) {
            weatherRepository.fetchHistoryWeather(query = query).collect { result ->
                result.onSuccess { historyWeather ->
                    _homeUiState.update {
                        it.copy(isLoading = false, historyWeather = historyWeather)
                    }
                }.onFailure {
                    _homeUiState.update { it.copy(isLoading = false, error = it.error) }
                }
            }
        }

    fun fetchUnitOfMeasurement() = viewModelScope.launch {
        weatherRepository.getMeasurementSettings().collect { unitsIndex ->
            _homeUiState.update { it.copy(unitOfMeasurement = MeasurementOptions.entries[unitsIndex]) }
        }
    }

    fun refresh() {
        fetchForecastWeather()
        fetchHistoryWeather()
    }
}
