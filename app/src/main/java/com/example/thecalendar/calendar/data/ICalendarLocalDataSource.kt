package com.example.thecalendar.calendar.data

import com.google.api.services.calendar.model.Event

interface ICalendarLocalDataSource {

    fun saveCalendarEvents(events: List<Event>)
    fun getAllCalendarEvents(): List<Event>
}