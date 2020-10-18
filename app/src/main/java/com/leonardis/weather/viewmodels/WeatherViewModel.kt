package com.leonardis.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardis.weather.models.Coordinates
import com.leonardis.weather.models.Location
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.repositories.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherViewModel: ViewModel() {

    private val repository: ForecastRepository = ForecastRepository()
    var location: Location? = null

    private val _suggestedWeatherData: MutableLiveData<List<WeatherResponse>> = MutableLiveData()
    val suggestedWeatherData: LiveData<List<WeatherResponse>> = _suggestedWeatherData

    private val _favoriteWeatherData: MutableLiveData<List<WeatherResponse>> = MutableLiveData()
    val favoriteWeatherData: LiveData<List<WeatherResponse>> = _favoriteWeatherData

    fun getSuggestedData(suggestedLocations: List<Coordinates>, unit: String) {
        val items: MutableList<WeatherResponse> = mutableListOf()

        viewModelScope.launch(Dispatchers.IO) {
            suggestedLocations.forEach {
                items.add(repository.getTodayWeather(it, unit))
            }

            _suggestedWeatherData.postValue(items)
        }
    }

    fun getFavoritesData(favoritesLocations: List<Coordinates?>, unit: String) {
        val items: MutableList<WeatherResponse> = mutableListOf()

        viewModelScope.launch(Dispatchers.IO) {
            favoritesLocations.forEach {
                it?.let {
                    items.add(repository.getTodayWeather(it, unit))
                }
            }

            _favoriteWeatherData.postValue(items.map { it.copy(isFavorite = true) })
        }
    }

}