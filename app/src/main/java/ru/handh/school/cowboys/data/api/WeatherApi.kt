package ru.handh.school.cowboys.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.handh.school.cowboys.data.entity.WeatherData

private const val API_KEY = "a8fad78f3c3836dcd5130b84faa43afc"
private const val DEFAULT_UNITS = "metric"

interface WeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = DEFAULT_UNITS
    ): WeatherData
}