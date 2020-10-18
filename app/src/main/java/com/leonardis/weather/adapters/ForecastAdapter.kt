package com.leonardis.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leonardis.weather.R
import com.leonardis.weather.adapters.viewholders.ForecastViewHolder
import com.leonardis.weather.models.Forecast

class ForecastAdapter: RecyclerView.Adapter<ForecastViewHolder>() {

    private val data: MutableList<Forecast> = mutableListOf()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ForecastViewHolder, position: Int) {
        val item = data[position]
        viewHolder.bind(item)
    }

    fun setData(forecast: List<Forecast>) {
        data.clear()
        data.addAll(forecast)
        notifyDataSetChanged()
    }
}



