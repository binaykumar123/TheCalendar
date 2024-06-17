package com.example.thecalendar.calendar.domain

import com.example.thecalendar.calendar.ui.DateItem
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class GetCalendarDaysUseCase @Inject constructor() : IGetCalendarDaysUseCase {

    override fun getCalendarDaysForMonth(year: Int, month: Int): List<DateItem> {
        val dates = getCalendarDateForGivenMonth(year, month)
        return dates.map {
            DateItem(it.date.toString())
        }
    }

    private fun getCalendarDateForGivenMonth(year: Int, month: Int): List<Date> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val dates = mutableListOf<Date>()
        for (i in 1..daysInMonth) {
            dates.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return dates
    }
}