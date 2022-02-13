package com.example.weatherlogger.main.model

sealed class LogListUiState {
    object Loading : LogListUiState()
    object Success : LogListUiState()
    object Error : LogListUiState()
}
