package com.example.thecalendar.calendar.calendarView.domain

interface IFetchAndSaveCalenderEventsUseCase {
    suspend fun fetchAllCalendarData()
}