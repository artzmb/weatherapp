package ru.handh.school.cowboys.data.repository

interface WeatherDataRepository {

    suspend fun getTemperature(latitude: Double, longitude: Double): Double
}