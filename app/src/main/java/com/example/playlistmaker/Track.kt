package com.example.playlistmaker

import java.text.SimpleDateFormat
import java.util.Locale

data class Track(
    val trackName: String,
    val artistName: String,
    private val trackTimeMillis: Long,
    val artworkUrl100: String
) {
    val trackTime: String
    get() = formatTime(trackTimeMillis)

    private fun formatTime(millis: Long): String {
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        return dateFormat.format(millis)
    }
}