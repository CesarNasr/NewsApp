package com.example.newsapp.data.localstorage

import android.content.Context
import android.content.SharedPreferences
import com.example.newsapp.R
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Session manager that saves and fetches authToken data from SharedPreferences
 */
class AuthLocalStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val AUTH_TOKEN = "auth_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthData(
        authToken: String?,
    ) {
        val editor = prefs.edit()
        authToken?.let { editor.putString(AUTH_TOKEN, authToken) }
        editor.apply()
    }

    fun clearAuthData(){
        val editor = prefs.edit()
        editor.clear().apply()
    }

    /**
     * Function to fetch auth token
     */
    fun getAuthToken(): String {
        return prefs.getString(AUTH_TOKEN, "").toString()
    }

}