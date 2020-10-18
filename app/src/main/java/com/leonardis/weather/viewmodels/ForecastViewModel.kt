package com.leonardis.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardis.weather.models.Coordinates
import com.leonardis.weather.models.ForecastResponse
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.repositories.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastViewModel: ViewModel() {

    private val repository: ForecastRepository = ForecastRepository()
    var weather: WeatherResponse? = null

    private val _forecastData: MutableLiveData<ForecastResponse> = MutableLiveData()
    val forecastData: LiveData<ForecastResponse> = _forecastData

    fun getForecast() {

        viewModelScope.launch(Dispatchers.IO) {
            weather?.coordinates?.let {
                _forecastData.postValue(repository.getForecast(it))
            }
        }
    }

}