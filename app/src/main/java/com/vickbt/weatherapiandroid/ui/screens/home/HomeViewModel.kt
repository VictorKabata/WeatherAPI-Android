package com.vickbt.weatherapiandroid.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.repository.datasource.WeatherRepositoryImpl
import com.vickbt.weatherapiandroid.utils.HomeUiStates
import io.github.aakira.napier.Napier
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
        fetchCurrentWeather(query = "Nairobi")
    }

    fun fetchCurrentWeather(query: String) = viewModelScope.launch(coroutineExceptionHandler) {
        weatherRepository.fetchCurrentWeather(query = query).collect {
            Napier.e(tag = "VicKbt", message = "Result: $it")
        }
    }

}