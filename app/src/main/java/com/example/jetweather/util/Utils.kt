package com.example.jetweather.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun formatDecimal(value: Double): String = "%.0f".format(value)

    fun formatDate(value: Int): String {
        val date = Date(value.toLong() * 1000)
        return SimpleDateFormat("EEE, MMM d").format(date).toString()
    }

    fun formatDateTime(value: Int): String {
        val date = Date(value.toLong() * 1000)
        return SimpleDateFormat("hh:mm:aa").format(date).toString()
    }
}