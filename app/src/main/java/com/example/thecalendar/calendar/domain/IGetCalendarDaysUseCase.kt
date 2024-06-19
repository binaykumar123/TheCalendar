package com.example.thecalendar.calendar.domain

import androidx.fragment.app.Fragment
import com.example.thecalendar.calendar.ui.DateItem

interface IGetCalendarDaysUseCase {
    suspend fun getCalendarDaysForMonth(year: Int, month: Int): List<DateItem>
}