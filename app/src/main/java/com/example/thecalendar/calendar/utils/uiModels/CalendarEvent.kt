package com.example.thecalendar.calendar.utils.uiModels

import com.google.api.client.util.DateTime

data class CalendarEvent(
    val startTime: DateTime? = null,
    val endTime: DateTime? = null,
    val summary: String? = null,
    val description: String? = null
)
