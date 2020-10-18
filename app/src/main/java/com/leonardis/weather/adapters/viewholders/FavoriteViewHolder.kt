package com.leonardis.weather.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leonardis.weather.R
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.repositories.ForecastRepository
import kotlinx.android.synthetic.main.item_favorite_view.view.delete
import kotlinx.android.synthetic.main.item_favorite_view.view.locationName
import kotlinx.android.synthetic.main.item_favorite_view.view.weatherImage
import kotlinx.android.synthetic.main.item_favorite_view.view.locationTemperature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

class FavoriteViewHolder(
    itemView: View,
    private val listener: (WeatherResponse) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: WeatherResponse) {
        itemView.locationName.text = location.name
        itemView.locationTemperature.text = itemView.context.getString(
            R.string.temperature, location.main?.temp?.roundToInt())
        runBlocking(Dispatchers.IO) {
            ForecastRepository().getImageBitmap(location.weather?.first()?.icon.orEmpty()).let {
                itemView.weatherImage.setImageBitmap(it)
            }
        }
        itemView.delete.setOnClickListener { listener(location) }
    }
}