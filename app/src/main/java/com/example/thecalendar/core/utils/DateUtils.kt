package com.example.thecalendar.core.utils

import com.google.api.client.util.DateTime
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    fun extractDayOfMonth(date: DateTime): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date(date.value)
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun getDayItem(dateTime: DateTime): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date(dateTime.value)
        return "${calendar.get(Calendar.DAY_OF_MONTH)}_${calendar.get(Calendar.MONTH)}_${
            calendar.get(
                Calendar.YEAR
            )
        }"
    }

    fun convertDateTimeToDateString(dateTime: DateTime?): String {
        if (dateTime == null)
            return ""
        val date = Date(dateTime.value)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

    fun convertDateTimeToTimeString(dateTime: DateTime?): String {
        if (dateTime == null)
            return ""
        val date = Date(dateTime.value)
        val timeFormat = SimpleDateFormat("hh:mma", Locale.getDefault())
        return timeFormat.format(date).replace("AM", "AM").replace("PM", "PM")
    }

    fun convertTimestampToDateString(timeStamp: Long): String {
        val date = Date(timeStamp)
        val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return format.format(date)
    }

    fun isCurrentDay(timestamp: Long?): Boolean {
        if (timestamp == null) {
            return false
        }
        val calendar = Calendar.getInstance()
        val today = calendar.timeInMillis

        calendar.timeInMillis = timestamp

        val dayOfYearTimestamp = calendar.get(Calendar.DAY_OF_YEAR)
        val yearTimestamp = calendar.get(Calendar.YEAR)

        calendar.timeInMillis = today

        val dayOfYearToday = calendar.get(Calendar.DAY_OF_YEAR)
        val yearToday = calendar.get(Calendar.YEAR)

        return dayOfYearTimestamp == dayOfYearToday && yearTimestamp == yearToday
    }

}