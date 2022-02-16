package com.example.weatherlogger.common.mapper

import com.example.weatherlogger.BuildConfig
import com.example.weatherlogger.database.model.WeatherDataModel
import com.example.weatherlogger.main.model.DetailsUiModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private const val DATE_TIME_PATTERN = "yyyy. MM. dd. HH:mm:ss"

fun WeatherDataModel.toUiModel() =
    DetailsUiModel(
        id = id,
        temperature = temperature,
        feelsLike = feelsLike,
        description = description,
        icon = "${BuildConfig.BASE_PHOTO_URL}$icon@2x.png",
        humidity = humidity,
        windSpeed = windSpeed,
        dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(dateTimeInMillis),
            ZoneId.systemDefault()
        ).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))
    )
