package com.example.weatherlogger.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherlogger.database.model.WeatherDataModel
import com.example.weatherlogger.database.tuple.WeatherLogTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert
    suspend fun insertWeather(model: WeatherDataModel)

    @Query(
        "SELECT id, temperature, feelsLike, description, dateTimeInMillis" +
            " FROM Weathers" +
            " ORDER BY id DESC"
    )
    fun getWeatherList(): Flow<List<WeatherLogTuple>>

    @Query(
        "SELECT *" +
            " FROM Weathers" +
            " WHERE id = :id"
    )
    suspend fun getWeatherDetails(id: Long): WeatherDataModel
}
