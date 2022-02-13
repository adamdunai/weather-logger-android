package com.example.weatherlogger.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WindWeatherResponseApiModel(
    val speed: Double,
)
