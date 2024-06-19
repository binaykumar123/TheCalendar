package com.example.thecalendar.calendar.ui

import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event

data class DateItem(
    val isDummyDay: Boolean = false,
    val dateTime: DateTime? = null,
    val displayDate: Int? = null,  //1, 2 .. 31 etc,
    val events: List<Event>? = null
)

data class DayItem(
    val date: Int,
    val month: Int
)

