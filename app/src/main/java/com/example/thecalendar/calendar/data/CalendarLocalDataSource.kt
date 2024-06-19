package com.example.thecalendar.calendar.data

import com.google.api.services.calendar.model.Event
import javax.inject.Inject

class CalendarLocalDataSource @Inject constructor() : ICalendarLocalDataSource {

    private var allCalendarEvents = emptyList<Event>()

    override fun saveCalendarEvents(events: List<Event>) {
        allCalendarEvents = events
    }

    override fun getAllCalendarEvents(): List<Event> {
        return allCalendarEvents
    }
}