package com.example.weatherlogger.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherWeatherResponseApiModel(
    val description: String,
    val icon: String,
)
