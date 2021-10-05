package ru.handh.school.cowboys.data.local

import android.content.SharedPreferences

class PreferencesImpl(private val sharedPreferences: SharedPreferences) : Preferences {

    companion object {
        private const val LATITUDE_KEY = "LATITUDE_KEY"
        private const val LONGITUDE_KEY = "LONGITUDE_KEY"
    }

    override var latitude: Float = 0.0f
        get() = sharedPreferences.getFloat(LATITUDE_KEY, 0.0f)
        set(value) {
            field = value
            sharedPreferences.edit()
                .putFloat(LATITUDE_KEY, value)
                .apply()
        }

    override var longitude: Float = 0.0f
        get() = sharedPreferences.getFloat(LONGITUDE_KEY, 0.0f)
        set(value) {
            field = value
            sharedPreferences.edit()
                .putFloat(LONGITUDE_KEY, value)
                .apply()
        }
}