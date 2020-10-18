package com.leonardis.weather.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.leonardis.weather.models.WeatherResponse


object SharedPreferencesUtils {
    private const val PREF_APP = "weatherApp"

    fun getBooleanData(context: Context, key: String): Boolean {
        return context.getSharedPreferences(
            PREF_APP,
            Context.MODE_PRIVATE
        ).getBoolean(key, false)
    }

    fun getIntData(context: Context, key: String): Int {
        return context.getSharedPreferences(
            PREF_APP,
            Context.MODE_PRIVATE
        ).getInt(key, 0)
    }

    fun getStringData(context: Context, key: String, defValue: String = ""): String {
        return context.getSharedPreferences(
            PREF_APP,
            Context.MODE_PRIVATE
        ).getString(key, defValue).toString()
    }

    fun saveData(
        context: Context,
        key: String?,
        value: String?
    ) {
        context.getSharedPreferences(
            PREF_APP,
            Context.MODE_PRIVATE
        ).edit().putString(key, value).apply()
    }

    fun saveData(context: Context, key: String?, value: Int) {
        context.getSharedPreferences(
            PREF_APP,
            Context.MODE_PRIVATE
        ).edit().putInt(key, value).apply()
    }

    fun saveData(
        context: Context,
        key: String?,
        value: Boolean
    ) {
        context.getSharedPreferences(
            PREF_APP,
            Context.MODE_PRIVATE
        )
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun getSharedPrefEditor(
        context: Context,
        pref: String?
    ): SharedPreferences.Editor {
        return context.getSharedPreferences(pref, Context.MODE_PRIVATE).edit()
    }

    fun saveData(editor: SharedPreferences.Editor) {
        editor.apply()
    }

    fun saveFavorites(
        context: Context,
        favorites: List<WeatherResponse>
    ) {
        val settings: SharedPreferences = context.getSharedPreferences(
            PREF_APP,
            Context.MODE_PRIVATE
        )

        val jsonFavorites = Gson().toJson(favorites)

        val editor: SharedPreferences.Editor
        editor = settings.edit()
        editor.putString(FAVORITES, jsonFavorites)
        editor.apply()
    }

    fun addFavorite(context: Context, item: WeatherResponse) {
        val favorites: MutableList<WeatherResponse> = mutableListOf<WeatherResponse>().apply {
            addAll(getFavorites(context))
            add(item)
        }
        saveFavorites(context, favorites)
    }

    fun removeFavorite(context: Context, item: WeatherResponse) {
        val items: MutableList<WeatherResponse> = getFavorites(context).toMutableList()
        val favorites: MutableList<WeatherResponse> = mutableListOf<WeatherResponse>().apply {
            items.remove(item)
            addAll(items)
        }
        saveFavorites(context, favorites)
    }

    fun getFavorites(context: Context): List<WeatherResponse> {
        val preferences = context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE)
        return if (preferences.contains(FAVORITES)) {
            val jsonFavorites = preferences.getString(FAVORITES, null)
            val favoriteItems: Array<WeatherResponse> = Gson().fromJson<Array<WeatherResponse>>(
                jsonFavorites,
                Array<WeatherResponse>::class.java
            )
            favoriteItems.toMutableList()
        } else {
            emptyList()
        }
    }

}