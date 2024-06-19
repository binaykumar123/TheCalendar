package com.example.thecalendar.calendar.events.domain

import com.example.thecalendar.calendar.data.ICalendarRepository
import com.example.thecalendar.calendar.utils.uiModels.CalendarEvent
import com.example.thecalendar.calendar.utils.mapCalendarEvent
import com.example.thecalendar.core.utils.DateUtils.getDayItem
import com.google.api.client.util.DateTime
import javax.inject.Inject

class FetchDayEventsUseCase @Inject constructor(
    private val iCalendarRepository: ICalendarRepository
) : IFetchDayEventsUseCase {
    override suspend fun fetchEventsListForDate(date: DateTime): List<CalendarEvent> {
        val allEvents = iCalendarRepository.getCalendarEvents()
        return allEvents.filter {
            it.start.dateTime != null && getDayItem(it.start.dateTime) == getDayItem(date)
        }.map {
            mapCalendarEvent(it)
        }
    }

}