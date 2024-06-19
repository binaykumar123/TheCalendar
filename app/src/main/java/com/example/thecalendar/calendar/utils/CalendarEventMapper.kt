package com.example.thecalendar.calendar.utils

import com.example.thecalendar.calendar.utils.uiModels.CalendarEvent
import com.google.api.services.calendar.model.Event

fun mapCalendarEvent(event: Event): CalendarEvent {
    val calendarEvent = CalendarEvent(
        startTime = event.start.dateTime,
        endTime = event.end.dateTime,
        summary = event.summary,
        description = event.description
    )

    return calendarEvent
}