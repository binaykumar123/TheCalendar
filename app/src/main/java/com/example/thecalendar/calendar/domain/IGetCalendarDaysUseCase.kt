package com.example.thecalendar.calendar.domain

import com.example.thecalendar.calendar.ui.DateItem

interface IGetCalendarDaysUseCase {
    fun getCalendarDaysForMonth(year: Int, month: Int): List<DateItem>
}