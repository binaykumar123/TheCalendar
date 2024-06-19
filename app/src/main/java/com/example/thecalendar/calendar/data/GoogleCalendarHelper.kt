package com.example.thecalendar.calendar.data

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.Calendar.Events
import com.google.api.services.calendar.model.Event
import javax.inject.Inject

class GoogleCalendarHelper @Inject constructor(
    private val iGoogleCredentialsHelper: IGoogleCredentialsHelper
) : IGoogleCalendarHelper {

    var calendar: Calendar? = null

    override suspend fun getEvents(): List<Event> {
        if (calendar == null) {
            initCalendar()
        }

        calendar?.let {
            val events = it.events().list("primary")
                //.setTimeMin(startTime)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute()
            Log.d("TAG", "getEvents: events: $events")
            val items = events.items
            Log.d("TAG", "getEvents: items: $items")
            return items
        }

        return emptyList()
    }

    private fun initCalendar() {
        val credential = iGoogleCredentialsHelper.getCredentials()
        val transport = NetHttpTransport()
        val jsonFactory = GsonFactory.getDefaultInstance()
        calendar = Calendar.Builder(
            transport, jsonFactory, credential
        ).setApplicationName("TheCalendar").build()
    }

}