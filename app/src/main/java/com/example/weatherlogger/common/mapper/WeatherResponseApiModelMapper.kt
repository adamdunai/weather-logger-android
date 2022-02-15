package com.example.weatherlogger.common.mapper

import com.example.weatherlogger.api.model.WeatherResponseApiModel
import com.example.weatherlogger.database.model.WeatherDataModel
import kotlin.math.roundToInt

fun WeatherResponseApiModel.toDataModel(
    dateTimeInMillis: Long,
    latitude: Double,
    longitude: Double,
) =
    WeatherDataModel(
        temperature = main.temperature.roundToInt(),
        feelsLike = main.feelsLike.roundToInt(),
        description = weather.first().description,
        icon = weather.first().icon,
        humidity = main.humidity,
        windSpeed = wind.speed,
        dateTimeInMillis = dateTimeInMillis,
        latitude = latitude,
        longitude = longitude
    )
