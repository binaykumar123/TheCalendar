package com.example.thecalendar.calendar.utils.uiModels

sealed interface CalendarDataState {
    data class Success(val days: List<DateItem>) : CalendarDataState
    data object Error : CalendarDataState
    data object Loading : CalendarDataState
}