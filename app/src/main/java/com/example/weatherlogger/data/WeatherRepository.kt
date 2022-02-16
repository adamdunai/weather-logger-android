package com.example.weatherlogger.data

import com.example.weatherlogger.api.ApiClient
import com.example.weatherlogger.common.mapper.toDataModel
import com.example.weatherlogger.database.AppDatabase
import com.example.weatherlogger.database.model.WeatherDataModel
import com.example.weatherlogger.database.tuple.WeatherLogTuple
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val appDatabase: AppDatabase,
) {

    val weatherLogStream: Flow<List<WeatherLogTuple>> =
        appDatabase.weatherDao().getWeatherList()

    suspend fun fetchWeatherData(latitude: Double, longitude: Double) {
        val response = apiClient.getWeatherData(latitude, longitude)

        appDatabase.weatherDao().insertWeather(
            response.toDataModel(
                dateTimeInMillis = Instant.now().toEpochMilli(),
                latitude = latitude,
                longitude = longitude
            )
        )
    }

    suspend fun getDetails(logId: Long): WeatherDataModel =
        appDatabase.weatherDao().getWeatherDetails(logId)
}
