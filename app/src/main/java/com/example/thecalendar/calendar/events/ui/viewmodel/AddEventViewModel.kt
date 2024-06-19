package com.example.thecalendar.calendar.events.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecalendar.calendar.events.domain.ICreateEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val iCreateEventUseCase: ICreateEventUseCase
) : ViewModel() {

    private var selectedStartTime: Date? = null
    private var selectedEndTime: Date? = null

    fun createEvent(summary: String) {
        viewModelScope.launch(Dispatchers.IO) {
            iCreateEventUseCase.createCalendarEvent(
                selectedStartTime!!,
                selectedEndTime!!,
                summary
            )
        }
    }

    fun setDate(timeStamp: Long) {
        selectedStartTime = Date(timeStamp)
        selectedEndTime = Date(timeStamp)
    }

    fun setStartTime(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.time = selectedStartTime ?: Date()

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        selectedStartTime = calendar.time
    }

    fun setEndTime(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.time = selectedEndTime ?: Date()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        selectedEndTime = calendar.time
    }
}