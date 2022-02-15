package com.example.weatherlogger.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weathers")
data class WeatherDataModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val temperature: Int,
    val feelsLike: Int,
    val description: String,
    val icon: String,
    val humidity: Int,
    val windSpeed: Double,
    val dateTimeInMillis: Long,
    val latitude: Double,
    val longitude: Double,
)
