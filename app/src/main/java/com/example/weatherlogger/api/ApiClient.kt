package com.example.weatherlogger.api

import com.example.weatherlogger.api.model.WeatherResponseApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): WeatherResponseApiModel
}
