package com.example.newsapp.presentation.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun View.snack(message: String) {
    val snack = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snack.show()
}


/*
fun String.dateReformat(date : String): Long {
    val date: Date
    val df = SimpleDateFormat("yyyyMMddHHmmss", Locale.US)

    date = try {
        df.parse(java.lang.String.valueOf(_time.getTime())) as Date
    } catch (e: ParseException) {
        throw RuntimeException("Failed to parse date: ", e)
    }

    return date.time
}*/
