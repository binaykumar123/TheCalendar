package com.example.thecalendar.calendar.domain

import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event

interface IFetchDayEventsUseCase {
    suspend fun fetchEventsListForDate(date: DateTime): List<Event>
}