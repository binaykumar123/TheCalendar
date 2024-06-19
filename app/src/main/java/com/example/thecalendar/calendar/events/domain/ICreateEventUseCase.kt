package com.example.thecalendar.calendar.events.domain

import java.util.Date

interface ICreateEventUseCase {
    suspend fun createCalendarEvent(
        startTime: Date,
        endTime: Date,
        summary: String
    )
}