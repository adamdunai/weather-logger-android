package com.example.weatherlogger.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponseApiModel(
    val weather: List<WeatherWeatherResponseApiModel>,
    val main: MainWeatherResponseApiModel,
    val wind: WindWeatherResponseApiModel,
)
