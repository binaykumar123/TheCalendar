package com.example.thecalendar.calendar.domain

import com.example.thecalendar.calendar.data.ICalendarRepository
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import java.util.Calendar
import java.util.Date
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

    private fun getDayItem(dateTime: DateTime): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date(dateTime.value)
        return "${calendar.get(Calendar.DAY_OF_MONTH)}_${calendar.get(Calendar.MONTH)}_${
            calendar.get(
                Calendar.YEAR
            )
        }"
    }
}