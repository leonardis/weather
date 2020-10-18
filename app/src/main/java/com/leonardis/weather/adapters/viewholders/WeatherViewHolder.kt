package com.leonardis.weather.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leonardis.weather.R
import com.leonardis.weather.models.Location
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.repositories.ForecastRepository
import kotlinx.android.synthetic.main.item_forecast.view.forecastImage
import kotlinx.android.synthetic.main.item_full_weather_view.view.locationName
import kotlinx.android.synthetic.main.item_full_weather_view.view.locationTemperature
import kotlinx.android.synthetic.main.item_full_weather_view.view.weatherImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: WeatherResponse) {
        itemView.locationName.text = location.name
        itemView.locationTemperature.text = itemView.context.getString(
            R.string.temperature, location.main?.temp?.roundToInt())

        runBlocking(Dispatchers.IO) {
            ForecastRepository().getImageBitmap(location.weather?.first()?.icon.orEmpty()).let {
                itemView.weatherImage.setImageBitmap(it)
            }
        }
    }
}