package com.example.weatherlogger.database.tuple

data class WeatherLogTuple(
    val id: Long,
    val temperature: Int,
    val feelsLike: Int,
    val description: String,
    val humidity: Int,
    val windSpeed: Double,
    val dateTimeInMillis: Long,
)
