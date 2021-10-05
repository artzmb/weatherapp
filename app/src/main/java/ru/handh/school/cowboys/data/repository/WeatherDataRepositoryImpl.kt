package ru.handh.school.cowboys.data.repository

import ru.handh.school.cowboys.data.api.WeatherApi

class WeatherDataRepositoryImpl(private val apiClient: WeatherApi) : WeatherDataRepository {

    override fun getTemperature(latitude: Double, longitude: Double): Double {
        val data = apiClient.getCurrentWeatherData(latitude, longitude)
        return data.main.temp
    }
}
