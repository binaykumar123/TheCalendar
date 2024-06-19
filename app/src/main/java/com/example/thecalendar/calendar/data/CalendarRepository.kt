package com.example.thecalendar.calendar.data

import com.example.thecalendar.calendar.data.models.EventCreationRequest
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

    override suspend fun createCalendarEvent(creationRequest: EventCreationRequest) {
        iGoogleCalendarHelper.createEvent(creationRequest)
        fetchAllCalendarEventsFromRemote()
    }

    private fun saveCalendarEvents(events: List<Event>) {
        allCalendarEvents = events
    }

    private fun getAllCalendarEvents(): List<Event> {
        return allCalendarEvents
    }

}