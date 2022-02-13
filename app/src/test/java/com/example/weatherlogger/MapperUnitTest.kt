package com.example.weatherlogger

import com.example.weatherlogger.api.model.MainWeatherResponseApiModel
import com.example.weatherlogger.api.model.WeatherResponseApiModel
import com.example.weatherlogger.api.model.WeatherWeatherResponseApiModel
import com.example.weatherlogger.api.model.WindWeatherResponseApiModel
import com.example.weatherlogger.common.mapper.toDataModel
import com.example.weatherlogger.database.model.WeatherDataModel
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperUnitTest {

    @Test
    fun weatherResponseApiModelMapper_Correct() {
        val apiModel = WeatherResponseApiModel(
            weather = listOf(
                WeatherWeatherResponseApiModel(
                    description = "few clouds",
                    icon = "02n"
                )
            ),
            main = MainWeatherResponseApiModel(
                temperature = 3.2,
                feelsLike = 3.8,
                humidity = 58
            ),
            wind = WindWeatherResponseApiModel(3.75)
        )

        assertEquals(
            WeatherDataModel(
                temperature = 3,
                feelsLike = 4,
                description = "few clouds",
                icon = "02n",
                humidity = 58,
                windSpeed = 3.75,
                dateTimeInMillis = 1644776336449L,
                latitude = 47.5559396,
                longitude = 19.0166011
            ),
            apiModel.toDataModel(
                dateTimeInMillis = 1644776336449L,
                latitude = 47.5559396,
                longitude = 19.0166011
            )
        )
    }
}
