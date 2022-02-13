package com.example.weatherlogger.api.interceptor

import com.example.weatherlogger.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultQueryParametersInterceptor @Inject constructor() : Interceptor {

    companion object {
        const val QUERY_PARAMETER_MODE_KEY = "mode"
        const val QUERY_PARAMETER_MODE_VALUE = "json"
        const val QUERY_PARAMETER_UNITS_KEY = "units"
        const val QUERY_PARAMETER_UNITS_VALUE = "metric"
        const val QUERY_PARAMETER_API_KEY_KEY = "appid"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(
                QUERY_PARAMETER_API_KEY_KEY,
                BuildConfig.OPENWEATHER_API_KEY
            )
            .addQueryParameter(
                QUERY_PARAMETER_MODE_KEY,
                QUERY_PARAMETER_MODE_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_UNITS_KEY,
                QUERY_PARAMETER_UNITS_VALUE
            )
            .build()

        request = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
