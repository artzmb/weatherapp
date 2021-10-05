package ru.handh.school.cowboys.data.repository

interface WeatherDataRepository {

    fun getTemperature(latitude: Double, longitude: Double): Double
}