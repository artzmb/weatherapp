package ru.handh.school.cowboys.presentation.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.runBlocking
import ru.handh.school.cowboys.R
import ru.handh.school.cowboys.data.api.createWeatherApiClient
import ru.handh.school.cowboys.data.repository.WeatherDataRepositoryImpl
import ru.handh.school.cowboys.databinding.FragmentMainBinding
import ru.handh.school.cowboys.domain.usecase.GetTemperatureUseCase

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val getTemperatureUseCase by lazy {
        GetTemperatureUseCase(WeatherDataRepositoryImpl(createWeatherApiClient()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonLoadTemperature.setOnClickListener {
            loadTemperature()
        }
        binding.textLongitude.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                event.action == KeyEvent.ACTION_DOWN &&
                event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                loadTemperature()
                true
            } else {
                false
            }
        }
    }

    private fun loadTemperature() {
        val params = GetTemperatureUseCase.Params(
            latitude = binding.textLatitude.toString().toDoubleOrNull() ?: 0.0,
            longitude = binding.textLongitude.toString().toDoubleOrNull() ?: 0.0
        )
        runBlocking {
            val currentTemperature = getTemperatureUseCase(params)
            // ~~~
            Snackbar.make(
                requireView(),
                getString(R.string.main_temperature_now, currentTemperature),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}