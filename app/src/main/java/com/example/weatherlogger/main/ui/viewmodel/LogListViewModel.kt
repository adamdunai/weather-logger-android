package com.example.weatherlogger.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherlogger.common.mapper.toUiModel
import com.example.weatherlogger.data.WeatherRepository
import com.example.weatherlogger.main.model.LogItemUiModel
import com.example.weatherlogger.main.model.LogListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogListViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LogListUiState>(LogListUiState.Success)
    val uiState: StateFlow<LogListUiState> = _uiState

    val weatherLogFlow: Flow<List<LogItemUiModel>> =
        repository.weatherLogStream
            .map { list ->
                list.map { it.toUiModel() }
            }

    fun fetchWeatherData(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = LogListUiState.Loading
            try {
                repository.fetchWeatherData(latitude, longitude)
                _uiState.value = LogListUiState.Success
            } catch (exception: Exception) {
                _uiState.value = LogListUiState.Error
            }
        }
    }
}
