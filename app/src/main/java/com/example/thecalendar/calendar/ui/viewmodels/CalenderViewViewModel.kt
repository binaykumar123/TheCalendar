package com.example.thecalendar.calendar.ui.viewmodels

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecalendar.calendar.domain.IFetchAndSaveCalenderEventsUseCase
import com.example.thecalendar.calendar.domain.IGetCalendarDaysUseCase
import com.example.thecalendar.calendar.ui.DateItem
import com.example.thecalendar.calendar.ui.uiModels.CalendarDataState
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CalenderViewViewModel @Inject constructor(
    private val iGetCalendarDaysUseCase: IGetCalendarDaysUseCase,
    private val iFetchAndSaveCalenderEventsUseCase: IFetchAndSaveCalenderEventsUseCase
) : ViewModel() {

    private val TAG = "CalenderViewViewModel"

    private var monthToShow: Int = 4
    private var yearToShow: Int = 2024

    private val _dateItemsList = MutableLiveData<CalendarDataState>()
    val dateItemList: LiveData<CalendarDataState>
        get() = _dateItemsList

    private val monthMap = mapOf(
        0 to "January",
        1 to "February",
        2 to "March",
        3 to "April",
        4 to "May",
        5 to "June",
        6 to "July",
        7 to "August",
        8 to "September",
        9 to "October",
        10 to "November",
        11 to "December"
    )


    init {
        setCurrentMonthAndYear()
        viewModelScope.launch(Dispatchers.IO) {
            iFetchAndSaveCalenderEventsUseCase.fetchAllCalendarData()
        }
    }

    fun fetchCalendarDates(fragment: Fragment) {
        _dateItemsList.postValue(CalendarDataState.Loading)
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val items = iGetCalendarDaysUseCase.getCalendarDaysForMonth(yearToShow, monthToShow)
                    .toMutableList()
                for (i in 0 until getFirstDayIndex()) {
                    items.add(0, DateItem(isDummyDay = true))
                }
                _dateItemsList.postValue(CalendarDataState.Success(items))
            }
        } catch (e: UserRecoverableAuthIOException) {
            fragment.startActivityForResult(e.intent, 1001)
        }
    }

    fun increaseMonth(fragment: Fragment) {
        if (monthToShow <= 10) {
            monthToShow++
        } else {
            monthToShow = 0
            yearToShow++
        }

        fetchCalendarDates(fragment)
    }

    fun decreaseMonth(fragment: Fragment) {
        if (monthToShow > 0) {
            monthToShow--
        } else {
            monthToShow = 11
            yearToShow--
        }

        fetchCalendarDates(fragment)
    }

    private fun setCurrentMonthAndYear() {
        val calendar = Calendar.getInstance()
        yearToShow = calendar.get(Calendar.YEAR)
        monthToShow = calendar.get(Calendar.MONTH)
        Log.d(TAG, "setMonthAndYear: month: $monthToShow year: $yearToShow")
    }

    fun getMonthDisplayName(): String {
        return "${monthMap[monthToShow]} $yearToShow"
    }

    private fun getFirstDayIndex(): Int {
        val calendar = Calendar.getInstance()
        calendar.set(yearToShow, monthToShow, 1)
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        return day - 1
    }
}