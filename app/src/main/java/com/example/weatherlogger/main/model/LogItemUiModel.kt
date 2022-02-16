package com.example.weatherlogger.main.model

data class LogItemUiModel(
    val id: Long,
    val temperature: Int,
    val feelsLike: Int,
    val description: String,
    val dateTime: String,
)
