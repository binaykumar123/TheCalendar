package com.example.thecalendar.calendar.calendarView.domain

import com.example.thecalendar.calendar.data.ICalendarRepository
import com.example.thecalendar.calendar.utils.uiModels.DateItem
import com.example.thecalendar.calendar.utils.mapCalendarEvent
import com.example.thecalendar.core.utils.DateUtils
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import java.util.Calendar
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
            events.filter { it.start.dateTime != null }
                .groupBy { DateUtils.getDayItem(it.start.dateTime) }

        return dates.map { date ->
            val dateEvents = eventsByDate[DateUtils.getDayItem(date)] ?: emptyList()
            DateItem(
                dateTime = date,
                events = dateEvents.map { mapCalendarEvent(it) },
                displayDate = DateUtils.extractDayOfMonth(date)
            )
        }
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

}