package com.leonardis.weather.network

import android.net.Uri
import com.google.gson.Gson
import com.leonardis.weather.BuildConfig
import com.leonardis.weather.models.WeatherResponse
import com.leonardis.weather.utils.APP_ID
import com.leonardis.weather.utils.BASE_URL
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder

fun buildURL(endpoint: String, vararg params: Pair<String, String> = emptyArray()): URL =
    URL(URLDecoder.decode(Uri.Builder().apply {
        scheme("http")
        authority(BASE_URL)
        appendPath(endpoint)
        appendQueryParameter(APP_ID, BuildConfig.API_KEY)
        params.forEach {
            appendQueryParameter(it.first, it.second)
        }
    }.build().toString(), "UTF-8"))

fun HttpURLConnection.buildGETRequest(): HttpURLConnection {
    return this.apply {
        requestMethod = "GET"
        setRequestProperty("Content-Type", "application/json; utf-8")
        setRequestProperty("Accept", "application/json")
        doOutput = true
    }
}

fun <T> parseResponse(inputStream: InputStream, type: Class<T>): T {
    val reader = BufferedReader(InputStreamReader(inputStream))
    val line: String = reader.readLine()
    reader.close()
    return Gson().fromJson<T>(line, type)
}