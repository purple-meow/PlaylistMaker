package com.example.playlistmaker

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

const val SHARED_PREFS = "shared_prefs"
const val THEMES_KEY = "themes_key"
const val TRACK_HISTORY_KEY = "track_history_key"

class App : Application() {

    var darkTheme = false
    lateinit var sharedPrefs: SharedPreferences
    lateinit var searchHistory: SearchHistory
    override fun onCreate() {
        super.onCreate()
        sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        searchHistory = SearchHistory(sharedPrefs)

        darkTheme = sharedPrefs.getBoolean(THEMES_KEY, false)
        switchTheme(darkTheme)
    }

    fun switchTheme(darkThemeEnabled: Boolean) {
        darkTheme = darkThemeEnabled
        AppCompatDelegate.setDefaultNightMode(
            if (darkThemeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )
        sharedPrefs.edit()
            .putBoolean(THEMES_KEY, darkTheme)
            .apply()
    }
}