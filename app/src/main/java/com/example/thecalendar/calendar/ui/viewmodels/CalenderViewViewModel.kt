package com.example.thecalendar.calendar.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.thecalendar.calendar.domain.IGetCalendarDaysUseCase
import com.example.thecalendar.calendar.ui.DateItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CalenderViewViewModel @Inject constructor(
    private val iGetCalendarDaysUseCase: IGetCalendarDaysUseCase
) : ViewModel() {

    private var monthToShow: Int = 1
    private var yearToShow: Int = 2023

    fun getCalendarDates(): List<DateItem> {
        return iGetCalendarDaysUseCase.getCalendarDaysForMonth(yearToShow, monthToShow)
    }
}