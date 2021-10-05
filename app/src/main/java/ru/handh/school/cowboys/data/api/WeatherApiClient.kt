package ru.handh.school.cowboys.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.openweathermap.org"

fun createWeatherApiClient(): WeatherApi =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)
