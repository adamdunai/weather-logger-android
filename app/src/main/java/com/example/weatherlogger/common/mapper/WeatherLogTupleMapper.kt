package com.example.weatherlogger.common.mapper

import com.example.weatherlogger.database.tuple.WeatherLogTuple
import com.example.weatherlogger.main.model.LogItemUiModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private const val DATE_TIME_PATTERN = "yyyy. MM. dd. HH:mm:ss"

fun WeatherLogTuple.toUiModel() =
    LogItemUiModel(
        id = id,
        temperature = temperature,
        feelsLike = feelsLike,
        description = description,
        humidity = humidity,
        windSpeed = windSpeed,
        dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(dateTimeInMillis),
            ZoneId.systemDefault()
        ).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))
    )
