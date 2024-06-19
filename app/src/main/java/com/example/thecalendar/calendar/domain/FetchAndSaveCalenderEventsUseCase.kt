package com.example.thecalendar.calendar.domain

import com.example.thecalendar.calendar.data.ICalendarRepository
import javax.inject.Inject

class FetchAndSaveCalenderEventsUseCase @Inject constructor(
    private val iCalendarRepository: ICalendarRepository
) : IFetchAndSaveCalenderEventsUseCase {
    override suspend fun fetchAllCalendarData() {
        iCalendarRepository.fetchAllCalendarEventsFromRemote()
    }
}