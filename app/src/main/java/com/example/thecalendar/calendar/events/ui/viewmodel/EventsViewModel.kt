package com.example.thecalendar.calendar.events.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecalendar.calendar.events.domain.IFetchDayEventsUseCase
import com.example.thecalendar.calendar.utils.uiModels.CalendarEvent
import com.example.thecalendar.core.utils.DateUtils.convertTimestampToDateString
import com.google.api.client.util.DateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val iFetchDayEventsUseCase: IFetchDayEventsUseCase
) : ViewModel() {

    var dateString = ""
    var dateTimeStamp = 0L

    private val _eventList = MutableLiveData<List<CalendarEvent>>()
    val eventList: LiveData<List<CalendarEvent>>
        get() = _eventList

    fun setDateTime(timeStamp: Long) {
        dateTimeStamp = timeStamp
        dateString = convertTimestampToDateString(timeStamp)
    }

    fun fetchEvents() {
        fetchEventsForDate(dateTimeStamp)
    }


    private fun fetchEventsForDate(timeStamp: Long) {
        if (timeStamp == 0L) {
            return
        }
        viewModelScope.launch {
            val dateTime = DateTime(timeStamp)
            val events = iFetchDayEventsUseCase.fetchEventsListForDate(dateTime)
            _eventList.postValue(events)
        }
    }

}