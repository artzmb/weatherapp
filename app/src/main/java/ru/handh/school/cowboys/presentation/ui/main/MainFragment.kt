package ru.handh.school.cowboys.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.handh.school.cowboys.R
import ru.handh.school.cowboys.data.local.Preferences
import ru.handh.school.cowboys.data.local.PreferencesImpl
import ru.handh.school.cowboys.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

    private val preferences: Preferences by lazy {
        PreferencesImpl(
            requireContext().getSharedPreferences(
                getString(R.string.preferences_file_name),
                Context.MODE_PRIVATE
            )
        )
    }

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
        lifecycleScope.launch {
            val latitude = preferences.latitude
            val longitude = preferences.longitude
            withContext(Dispatchers.Main) {
                binding.textLatitude.setText(latitude.toString())
                binding.textLongitude.setText(longitude.toString())
            }
        }
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
        val latitude = binding.textLatitude.text.toString().toDoubleOrNull() ?: 0.0
        val longitude = binding.textLongitude.text.toString().toDoubleOrNull() ?: 0.0
        // ~~~
        viewModel.loadTemperature(latitude, longitude)
        preferences.latitude = latitude.toFloat()
        preferences.longitude = longitude.toFloat()
    }
}