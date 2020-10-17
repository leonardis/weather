package com.leonardis.weather.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardis.weather.models.Location
import com.leonardis.weather.repositories.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherViewModel(
    private val repository: ForecastRepository
) : ViewModel() {

    fun getCurrentWeather(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTodayWeather(location.coordinates)
                Log.e(this@WeatherViewModel.javaClass.simpleName, response.toString())
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

}