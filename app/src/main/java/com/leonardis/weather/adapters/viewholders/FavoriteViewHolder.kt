package com.leonardis.weather.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.repositories.ForecastRepository
import kotlinx.android.synthetic.main.item_favorite_view.view.locationName
import kotlinx.android.synthetic.main.item_forecast.view.forecastImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: WeatherResponse) {
        itemView.locationName.text = location.name
        runBlocking(Dispatchers.IO) {
            ForecastRepository().getImageBitmap(location.weather?.first()?.icon.orEmpty()).let {
                itemView.forecastImage.setImageBitmap(it)
            }
        }
    }
}