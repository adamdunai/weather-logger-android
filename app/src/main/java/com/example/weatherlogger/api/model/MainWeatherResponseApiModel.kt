package com.example.weatherlogger.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainWeatherResponseApiModel(
    @Json(name = "temp")
    val temperature: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    val humidity: Int,
)
