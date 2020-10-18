package com.leonardis.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leonardis.weather.R
import com.leonardis.weather.adapters.viewholders.FavoriteViewHolder
import com.leonardis.weather.adapters.viewholders.WeatherViewHolder
import com.leonardis.weather.models.Location
import com.leonardis.weather.utils.MainViewTypes

class LocationsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<Location> = mutableListOf()

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return if (data[position].isFavorite) {
            MainViewTypes.FAVORITE.ordinal
        } else {
            MainViewTypes.WEATHER.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MainViewTypes.WEATHER.ordinal -> WeatherViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_full_weather_view, parent, false))
            else -> FavoriteViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite_view, parent, false))
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is WeatherViewHolder) {
            viewHolder.bind(data[position])
        } else {
            (viewHolder as FavoriteViewHolder).bind(data[position])
        }
    }

    fun setData(locations: List<Location>) {
        data.clear()
        data.addAll(locations)
        notifyDataSetChanged()
    }
}



