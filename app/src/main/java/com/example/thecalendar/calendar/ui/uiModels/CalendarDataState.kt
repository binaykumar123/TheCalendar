package com.example.thecalendar.calendar.ui.uiModels

import com.example.thecalendar.calendar.ui.DateItem

sealed interface CalendarDataState {
    data class Success(val days: List<DateItem>) : CalendarDataState
    data object Error : CalendarDataState
    data object Loading : CalendarDataState
}