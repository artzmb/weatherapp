package ru.handh.school.cowboys.domain.usecase

import ru.handh.school.cowboys.data.repository.WeatherDataRepository

class GetTemperatureUseCase(
    private val weatherDataRepository: WeatherDataRepository
) {

    suspend operator fun invoke(params: Params): Double =
        weatherDataRepository.getTemperature(params.latitude, params.longitude)

    data class Params(
        val latitude: Double,
        val longitude: Double
    )
}
