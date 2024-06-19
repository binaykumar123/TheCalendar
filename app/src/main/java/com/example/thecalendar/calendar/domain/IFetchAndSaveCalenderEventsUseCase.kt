package com.example.thecalendar.calendar.domain

interface IFetchAndSaveCalenderEventsUseCase {
    suspend fun fetchAllCalendarData()
}