package com.example.weatherlogger.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherlogger.BuildConfig
import com.example.weatherlogger.database.dao.WeatherDao
import com.example.weatherlogger.database.model.WeatherDataModel

@Database(
    entities = [
        WeatherDataModel::class
    ],
    version = BuildConfig.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}
