package com.leonardis.weather.repositories

import com.leonardis.weather.models.Coordinates
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.network.Result
import com.leonardis.weather.network.buildGETRequest
import com.leonardis.weather.network.buildURL
import com.leonardis.weather.network.parseResponse
import com.leonardis.weather.utils.CURRENT_WEATHER
import com.leonardis.weather.utils.LATITUDE
import com.leonardis.weather.utils.LONGITUDE
import com.leonardis.weather.utils.METRIC_UNITS
import com.leonardis.weather.utils.UNITS
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ForecastRepository {

    @Throws(Exception::class)
    fun getTodayWeather(location: Coordinates, unit: String = METRIC_UNITS): Result<WeatherResponse> {
        val weatherURL: URL = buildURL(CURRENT_WEATHER,
            Pair(LATITUDE, location.latitude.toString()),
            Pair(LONGITUDE, location.longitude.toString()),
            Pair(UNITS, unit)
        )

        (weatherURL.openConnection() as? HttpURLConnection)?.buildGETRequest()?.run {
            return Result.Success(parseResponse(inputStream, WeatherResponse::class.java))
        }
        return Result.Error(Exception("Cannot open the connection"))
    }

}
