package com.example.thecalendar.calendar.data

import androidx.fragment.app.Fragment
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import javax.inject.Inject

class CalendarRepository @Inject constructor(
    private val iGoogleCalendarHelper: IGoogleCalendarHelper
) : ICalendarRepository {

    private var allCalendarEvents = emptyList<Event>()

    override suspend fun getCalendarEvents(): List<Event> {
        val events = getAllCalendarEvents()
        if (events.isEmpty()) {
            return fetchAllCalendarEventsFromRemote()
        }

        return events
    }

    override suspend fun fetchAllCalendarEventsFromRemote(): List<Event> {
        val events = iGoogleCalendarHelper.getEvents()
        saveCalendarEvents(events)
        return events
    }

    private fun saveCalendarEvents(events: List<Event>) {
        allCalendarEvents = events
    }

    private fun getAllCalendarEvents(): List<Event> {
        return allCalendarEvents
    }

}