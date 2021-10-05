package ru.handh.school.cowboys.data.entity

data class WeatherData(
    val main: Main
) {
    // nested
    data class Main(
        val temp: Double
    )
}
