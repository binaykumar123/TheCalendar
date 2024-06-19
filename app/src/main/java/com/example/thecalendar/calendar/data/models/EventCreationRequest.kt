package com.example.thecalendar.calendar.data.models

import java.util.Date

data class EventCreationRequest(
    val startDateTime: Date,
    val endDateTime: Date,
    val summary: String,
    val description: String? = null,
)
