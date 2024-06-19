package com.example.thecalendar.calendar.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecalendar.calendar.domain.IFetchDayEventsUseCase
import com.example.thecalendar.core.utils.DateUtils.convertTimestampToDateString
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val iFetchDayEventsUseCase: IFetchDayEventsUseCase
) : ViewModel() {

    var date: Int = 0
    var month: Int = 0
    var year: Int = 0
    var dateString = ""
    var dateTimeStamp = 0L

    private val _eventList = MutableLiveData<List<Event>>()
    val eventList: LiveData<List<Event>>
        get() = _eventList

    fun setDateTime(timeStamp: Long) {
        val dateObjects = Date(timeStamp)
        date = dateObjects.date
        month = dateObjects.month
        year = dateObjects.year
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