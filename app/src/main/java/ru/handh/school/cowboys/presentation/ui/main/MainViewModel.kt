package ru.handh.school.cowboys.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.handh.school.cowboys.data.api.createWeatherApiClient
import ru.handh.school.cowboys.data.repository.WeatherDataRepositoryImpl
import ru.handh.school.cowboys.domain.usecase.GetTemperatureUseCase

class MainViewModel : ViewModel() {

    private val _currentTemperatureLiveData = MutableLiveData<Double>()
    val currentTemperatureLiveData: LiveData<Double> get() = _currentTemperatureLiveData

    private val getTemperatureUseCase by lazy {
        GetTemperatureUseCase(WeatherDataRepositoryImpl(createWeatherApiClient()))
    }

    fun loadTemperature(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val params = GetTemperatureUseCase.Params(latitude, longitude)
            val currentTemperature = getTemperatureUseCase(params)
            _currentTemperatureLiveData.postValue(currentTemperature)
        }
    }
}