package com.example.weatherlogger

import android.app.Application
import logcat.AndroidLogcatLogger
import logcat.LogPriority

class WeatherLoggerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidLogcatLogger.installOnDebuggableApp(this, LogPriority.VERBOSE)
    }
}
