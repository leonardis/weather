package com.leonardis.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leonardis.weather.R
import com.leonardis.weather.models.Coordinates
import com.leonardis.weather.models.Location
import com.leonardis.weather.repositories.ForecastRepository
import com.leonardis.weather.viewmodels.WeatherViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = ForecastRepository()
        val viewModel = WeatherViewModel(repository = repository)

        viewModel.getCurrentWeather(Location(coordinates = Coordinates(4.76466, -74.050378)))
    }
}