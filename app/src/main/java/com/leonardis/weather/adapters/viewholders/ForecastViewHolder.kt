package com.leonardis.weather.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leonardis.weather.R
import com.leonardis.weather.models.Forecast
import com.leonardis.weather.models.Location
import com.leonardis.weather.repositories.ForecastRepository
import com.leonardis.weather.utils.DAY_OF_THE_WEEK
import com.leonardis.weather.utils.FULL_DATE
import com.leonardis.weather.utils.convertDate
import kotlinx.android.synthetic.main.item_favorite_view.view.locationName
import kotlinx.android.synthetic.main.item_forecast.view.currentTemp
import kotlinx.android.synthetic.main.item_forecast.view.day
import kotlinx.android.synthetic.main.item_forecast.view.forecastImage
import kotlinx.android.synthetic.main.item_forecast.view.humidity
import kotlinx.android.synthetic.main.item_forecast.view.maxValue
import kotlinx.android.synthetic.main.item_forecast.view.minValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.math.roundToInt

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(forecast: Forecast) {
        val context = itemView.context
        itemView.minValue.text = context.getString(R.string.temperature, forecast.main.tempMin.roundToInt())
        itemView.maxValue.text = context.getString(R.string.temperature, forecast.main.tempMax.roundToInt())
        itemView.currentTemp.text = context.getString(R.string.temperature, forecast.main.temp.roundToInt())
        itemView.humidity.text = forecast.main.humidity.toString()
        itemView.day.text = convertDate(forecast.dt, DAY_OF_THE_WEEK)

        runBlocking(Dispatchers.IO) {
            ForecastRepository().getImageBitmap(forecast.weather.first().icon).let {
                itemView.forecastImage.setImageBitmap(it)
            }
        }
    }
}