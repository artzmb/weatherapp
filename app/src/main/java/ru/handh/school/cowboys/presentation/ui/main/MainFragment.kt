package ru.handh.school.cowboys.presentation.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.handh.school.cowboys.R
import ru.handh.school.cowboys.data.api.createWeatherApiClient
import ru.handh.school.cowboys.data.repository.WeatherDataRepositoryImpl
import ru.handh.school.cowboys.databinding.FragmentMainBinding
import ru.handh.school.cowboys.domain.usecase.GetTemperatureUseCase

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
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

    override fun onStart() {
        super.onStart()
        // ~~~
        viewModel.currentTemperatureLiveData.observe(this) {
            Snackbar.make(
                requireView(),
                getString(R.string.main_temperature_now, it),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun loadTemperature() {
        viewModel.loadTemperature(
            latitude = binding.textLatitude.toString().toDoubleOrNull() ?: 0.0,
            longitude = binding.textLongitude.toString().toDoubleOrNull() ?: 0.0
        )
    }
}