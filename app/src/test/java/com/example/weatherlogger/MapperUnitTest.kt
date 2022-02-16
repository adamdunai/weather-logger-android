package com.example.weatherlogger

import com.example.weatherlogger.api.model.MainWeatherResponseApiModel
import com.example.weatherlogger.api.model.WeatherResponseApiModel
import com.example.weatherlogger.api.model.WeatherWeatherResponseApiModel
import com.example.weatherlogger.api.model.WindWeatherResponseApiModel
import com.example.weatherlogger.common.mapper.toDataModel
import com.example.weatherlogger.common.mapper.toUiModel
import com.example.weatherlogger.database.model.WeatherDataModel
import com.example.weatherlogger.database.tuple.WeatherLogTuple
import com.example.weatherlogger.main.model.DetailsUiModel
import com.example.weatherlogger.main.model.LogItemUiModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.ZoneId
import java.util.TimeZone

class MapperUnitTest {

    @Before
    fun setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")))
    }

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

    @Test
    fun weatherLogTupleMapper_Correct() {
        val tuple = WeatherLogTuple(
            id = 1L,
            temperature = 3,
            feelsLike = 4,
            description = "few clouds",
            dateTimeInMillis = 1644776336449L
        )

        assertEquals(
            LogItemUiModel(
                id = 1L,
                temperature = 3,
                feelsLike = 4,
                description = "few clouds",
                dateTime = "2022. 02. 13. 19:18:56"
            ),
            tuple.toUiModel()
        )
    }

    @Test
    fun weatherDataModelMapper_Correct() {
        val dataModel = WeatherDataModel(
            id = 1L,
            temperature = 3,
            feelsLike = 4,
            description = "few clouds",
            icon = "02n",
            humidity = 58,
            windSpeed = 3.75,
            dateTimeInMillis = 1644776336449L,
            latitude = 47.5559396,
            longitude = 19.0166011
        )

        assertEquals(
            DetailsUiModel(
                id = 1L,
                temperature = 3,
                feelsLike = 4,
                description = "few clouds",
                icon = "https://openweathermap.org/img/wn/02n@2x.png",
                humidity = 58,
                windSpeed = 3.75,
                dateTime = "2022. 02. 13. 19:18:56"
            ),
            dataModel.toUiModel()
        )
    }
}
