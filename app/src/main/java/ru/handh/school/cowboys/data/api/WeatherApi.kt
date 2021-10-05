package ru.handh.school.cowboys.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    fun getCurrentWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): ResponseBody
}