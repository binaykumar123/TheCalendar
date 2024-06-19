package com.example.thecalendar.calendar.ui.calendarView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thecalendar.R
import com.example.thecalendar.calendar.ui.DateItem
import com.example.thecalendar.calendar.ui.adapters.CalenderViewAdapter
import com.example.thecalendar.calendar.ui.uiModels.CalendarDataState
import com.example.thecalendar.calendar.ui.viewmodels.CalenderViewViewModel
import com.example.thecalendar.databinding.FragmentCalenderViewBinding
import com.google.api.client.util.DateTime
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class CalendarViewFragment : Fragment() {

    private lateinit var binding: FragmentCalenderViewBinding
    private lateinit var calendarViewAdapter: CalenderViewAdapter
    private val viewModel by viewModels<CalenderViewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalenderViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setObservers()
        fetchCalendarDates()
        setClickListeners()
        binding.btnFetch.setOnClickListener {
            fetchCalendarDates()
        }
    }

    private fun initRecyclerView() {
        calendarViewAdapter = CalenderViewAdapter()
        binding.rvCalendar.apply {
            layoutManager = GridLayoutManager(requireContext(), 7)
            adapter = calendarViewAdapter
        }
    }

    private fun setObservers() {
        viewModel.dateItemList.observe(viewLifecycleOwner) {
            viewModel.getMonthDisplayName().also {
                binding.tvMonthName.text = it
            }
            if (it is CalendarDataState.Success) {
                toggleLoader(false)
                calendarViewAdapter.addItems(it.days) {
                    onDateClick(it)
                }
            } else if (it is CalendarDataState.Error) {
                toggleLoader(false)
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            } else {
                toggleLoader(true)
            }
        }
    }

    private fun onDateClick(item: DateItem) {
        item.dateTime?.value?.let {
            val action = CalendarViewFragmentDirections.actionCalendarFragmentToEventsFragment(it)
            findNavController().navigate(action)
        }
    }


    fun getDayAndMonth(dateTime: DateTime): Triple<Int, Int, Int> {
        val date = Date(dateTime.value)
        val calendar = Calendar.getInstance()
        calendar.time = date

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        return Triple(day, month, year)
    }

    private fun toggleLoader(show: Boolean) {
        binding.loader.isVisible = show
    }

    private fun fetchCalendarDates() {
        viewModel.fetchCalendarDates(this)
    }

    private fun setClickListeners() {
        binding.ivNextMonth.setOnClickListener {
            viewModel.increaseMonth(this)
        }
        binding.ivPreviousMonth.setOnClickListener {
            viewModel.decreaseMonth(this)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1001) {
            viewModel.fetchCalendarDates(this)
        }
    }
}