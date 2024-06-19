package com.example.thecalendar.calendar.calendarView.domain

import com.example.thecalendar.calendar.utils.uiModels.DateItem

interface IGetCalendarDaysUseCase {
    suspend fun getCalendarDaysForMonth(year: Int, month: Int): List<DateItem>
}