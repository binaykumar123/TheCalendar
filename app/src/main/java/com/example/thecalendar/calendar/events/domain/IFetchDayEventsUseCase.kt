package com.example.thecalendar.calendar.events.domain

import com.example.thecalendar.calendar.utils.uiModels.CalendarEvent
import com.google.api.client.util.DateTime

interface IFetchDayEventsUseCase {
    suspend fun fetchEventsListForDate(date: DateTime): List<CalendarEvent>
}