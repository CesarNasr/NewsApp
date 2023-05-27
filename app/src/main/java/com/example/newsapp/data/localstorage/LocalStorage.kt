package com.example.newsapp.data.localstorage

import android.content.Context
import android.content.SharedPreferences
import com.example.newsapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.sql.Timestamp
import javax.inject.Inject

/**
 * Storage manager that saves and fetches timestamp data from SharedPreferences
 */
class LocalStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val KEY_TIME_STAMP = "time_stamp"
    }

    /**
     * Function to save timestamp
     */
    fun saveTimeStampData(
        timestamp: Long,
    ) {
        val editor = prefs.edit()
        editor.putLong(KEY_TIME_STAMP, timestamp)
        editor.apply()
    }

    fun clearTimeStampData() {
        val editor = prefs.edit()
        editor.clear().apply()
    }

    /**
     * Function to fetch timestamp
     */
    fun getTimeStampData(): Long {
        return prefs.getLong(KEY_TIME_STAMP, -1)
    }

}