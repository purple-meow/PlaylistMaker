package com.example.playlistmaker

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

class SearchHistory(private val sharedPrefs : SharedPreferences) {
    private val maxHistorySize = 10

    fun addTrackToHistory(track: Track) {
        val currentHistory = getTrackHistory()
        val newHistory = currentHistory.filter { it.trackId != track.trackId }.toMutableList()
        newHistory.add(0, track)

        if (newHistory.size > maxHistorySize) {
            newHistory.removeAt(newHistory.size - 1)
        }

        sharedPrefs.edit()
            .putString(TRACK_HISTORY_KEY, Gson().toJson(newHistory))
            .apply()
    }

    fun getTrackHistory(): List<Track> {
        val json = sharedPrefs.getString(TRACK_HISTORY_KEY, null) ?: return emptyList()
        return  Gson().fromJson(json, Array<Track>::class.java).toList()
    }
    fun clearHistory() {
        sharedPrefs.edit()
            .remove(TRACK_HISTORY_KEY)
            .apply()
    }

}