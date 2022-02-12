package com.example.weatherlogger.main.model

data class LogItemUiModel(
    val id: Long,
    val temperature: Int,
    val feelsLike: Int,
    val description: String,
    val humidity: Int,
    val windSpeed: Double,
    val dateTime: String,
)
