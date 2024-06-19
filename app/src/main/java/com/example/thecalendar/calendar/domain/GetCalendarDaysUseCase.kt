package com.example.thecalendar.calendar.domain

import android.util.Log
import com.example.thecalendar.calendar.data.ICalendarRepository
import com.example.thecalendar.calendar.ui.DateItem
import com.example.thecalendar.calendar.ui.DayItem
import com.example.thecalendar.calendar.ui.uiModels.ParcelableDateTime
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class GetCalendarDaysUseCase @Inject constructor(
    private val iCalendarRepository: ICalendarRepository
) : IGetCalendarDaysUseCase {

    override suspend fun getCalendarDaysForMonth(
        year: Int,
        month: Int
    ): List<DateItem> {

        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1, 0, 0, 1)

        val dates = getCalendarDateForGivenMonth(calendar)
        val events = iCalendarRepository.getCalendarEvents()
        val eventsByDate: Map<String, List<Event>> =
            events.filter { it.start.dateTime != null }.groupBy { getDayItem(it.start.dateTime) }

        Log.d("TAG", "getCalendarDaysForMonth:  eventsByDate ${eventsByDate.size}")

        return dates.map { date ->
            val dateEvents = eventsByDate[getDayItem(date)] ?: emptyList()
            DateItem(
                dateTime = date,
                events = dateEvents,
                displayDate = extractDayOfMonth(date)
            )
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

    private fun getCalendarDateForGivenMonth(calendar: Calendar): List<DateTime> {
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val dates = mutableListOf<DateTime>()
        for (i in 1..daysInMonth) {
            dates.add(DateTime(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return dates
    }

    private fun extractDayOfMonth(date: DateTime): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date(date.value)
        return calendar.get(Calendar.DAY_OF_MONTH)
    }
}