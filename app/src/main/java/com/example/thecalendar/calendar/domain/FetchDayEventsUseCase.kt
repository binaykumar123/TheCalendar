package com.example.thecalendar.calendar.domain

import com.example.thecalendar.calendar.data.ICalendarRepository
import com.example.thecalendar.core.utils.DateUtils.getDayItem
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import javax.inject.Inject

class FetchDayEventsUseCase @Inject constructor(
    private val iCalendarRepository: ICalendarRepository
) : IFetchDayEventsUseCase {
    override suspend fun fetchEventsListForDate(date: DateTime): List<Event> {
        val allEvents = iCalendarRepository.getCalendarEvents()
        return allEvents.filter {
            it.start.dateTime != null && getDayItem(it.start.dateTime) == getDayItem(date)
        }
    }

}