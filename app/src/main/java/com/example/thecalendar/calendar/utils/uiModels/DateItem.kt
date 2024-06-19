package com.example.thecalendar.calendar.utils.uiModels

import com.google.api.client.util.DateTime

data class DateItem(
    val isDummyDay: Boolean = false,
    val dateTime: DateTime? = null,
    val displayDate: Int? = null,  //1, 2 .. 31 etc,
    val events: List<CalendarEvent>? = null
)

data class DayItem(
    val date: Int,
    val month: Int
)

