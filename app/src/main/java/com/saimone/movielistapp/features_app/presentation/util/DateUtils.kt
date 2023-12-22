package com.saimone.movielistapp.features_app.presentation.util

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.text.ParseException
import java.util.Locale

object DateUtils {
    private val inputFormat = SimpleDateFormat("d MMM yyyy", Locale.ENGLISH)

    fun formatReleaseDate(releasedDate: String): String {
        val outputFormat = SimpleDateFormat("yyyy, d MMM", Locale.ENGLISH)

        return try {
            val date = inputFormat.parse(releasedDate)
            outputFormat.format(date)
        } catch (e: ParseException) {
            Log.e("AAA", "Error parsing date: $releasedDate")
            ""
        }
    }

    fun getYear(releasedDate: String): String {
        val outputFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)

        return try {
            val date = inputFormat.parse(releasedDate)
            outputFormat.format(date)
        } catch (e: ParseException) {
            Log.e("AAA", "Error parsing date: $releasedDate")
            ""
        }
    }
}