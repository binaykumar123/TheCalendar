package com.example.thecalendar.calendar.domain

import com.example.thecalendar.calendar.data.ICalendarRepository
import com.example.thecalendar.calendar.data.models.EventCreationRequest
import com.google.api.client.util.DateTime
import java.util.Date
import javax.inject.Inject

class CreateEventUseCase @Inject constructor(
    private val iCalendarRepository: ICalendarRepository
) : ICreateEventUseCase {
    override suspend fun createCalendarEvent(
        startTime: Date,
        endTime: Date,
        summary: String
    ) {
        val request = EventCreationRequest(
            startDateTime = startTime,
            endDateTime = endTime,
            summary = summary
        )
        iCalendarRepository.createCalendarEvent(request)
    }


}