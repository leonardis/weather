package com.leonardis.weather.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.leonardis.weather.models.Coordinates
import com.leonardis.weather.models.ForecastResponse
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.network.buildGETRequest
import com.leonardis.weather.network.buildSimpleGETRequest
import com.leonardis.weather.network.buildURL
import com.leonardis.weather.network.parseResponse
import com.leonardis.weather.utils.CURRENT_WEATHER
import com.leonardis.weather.utils.FORECAST
import com.leonardis.weather.utils.ICON_URL
import com.leonardis.weather.utils.LATITUDE
import com.leonardis.weather.utils.LONGITUDE
import com.leonardis.weather.utils.METRIC_UNITS
import com.leonardis.weather.utils.UNITS
import kotlinx.coroutines.runBlocking
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ForecastRepository {

    @Throws(Exception::class)
    fun getTodayWeather(location: Coordinates, unit: String = METRIC_UNITS): WeatherResponse {
        val weatherURL: URL = buildURL(
            CURRENT_WEATHER,
            Pair(LATITUDE, location.latitude.toString()),
            Pair(LONGITUDE, location.longitude.toString()),
            Pair(UNITS, unit)
        )

        (weatherURL.openConnection() as? HttpURLConnection)?.buildGETRequest()?.run {
            return parseResponse(inputStream, WeatherResponse::class.java)
        }
        throw Exception("Cannot open the connection")
    }

    @Throws(Exception::class)
    fun getForecast(location: Coordinates, unit: String = METRIC_UNITS): ForecastResponse {
        val weatherURL: URL = buildURL(
            FORECAST,
            Pair(LATITUDE, location.latitude.toString()),
            Pair(LONGITUDE, location.longitude.toString()),
            Pair(UNITS, unit)
        )

        (weatherURL.openConnection() as? HttpURLConnection)?.buildGETRequest()?.run {
            return parseResponse(inputStream, ForecastResponse::class.java)
        }
        throw Exception("Cannot open the connection")
    }

    @Throws(Exception::class)
    fun getImageBitmap(iconCode: String): Bitmap {
        val iconURL: URL = URL("$ICON_URL$iconCode.png")

        return runBlocking {

            val connection =
                iconURL.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            val myBitmap = BitmapFactory.decodeStream(input)
            myBitmap
            //throw Exception("Cannot open the connection")
        }
    }
}
