package com.example.weatherlogger.main.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherlogger.common.mapper.toUiModel
import com.example.weatherlogger.data.WeatherRepository
import com.example.weatherlogger.main.model.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: WeatherRepository,
) : ViewModel() {

    companion object {
        private const val KEY_LOG_ID = "logId"
    }

    init {
        getDetails()
    }

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState

    fun getDetails() {
        savedStateHandle.get<Long>(KEY_LOG_ID)?.let { logId ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val details = repository.getDetails(logId)
                    _uiState.value = DetailsUiState.Success(details.toUiModel())
                } catch (exception: Exception) {
                    _uiState.value = DetailsUiState.Error
                }
            }
        }
    }
}
