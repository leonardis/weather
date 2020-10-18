package com.leonardis.weather.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leonardis.weather.models.Location
import kotlinx.android.synthetic.main.item_full_weather_view.view.locationName

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: Location) {
        itemView.locationName.text = location.name
    }
}