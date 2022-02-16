package com.example.weatherlogger.main.model

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val data: DetailsUiModel) : DetailsUiState()
    object Error : DetailsUiState()
}
