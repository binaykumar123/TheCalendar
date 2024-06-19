package com.example.thecalendar.calendar.data

import androidx.fragment.app.Fragment
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event

interface ICalendarRepository {

    suspend fun getCalendarEvents(): List<Event>

    suspend fun fetchAllCalendarEventsFromRemote(): List<Event>

}