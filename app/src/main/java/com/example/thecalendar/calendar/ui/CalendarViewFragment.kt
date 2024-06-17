package com.example.thecalendar.calendar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thecalendar.calendar.ui.adapters.CalenderViewAdapter
import com.example.thecalendar.calendar.ui.viewmodels.CalenderViewViewModel
import com.example.thecalendar.databinding.FragmentCalenderViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarViewFragment : Fragment() {

    private lateinit var binding: FragmentCalenderViewBinding
    private lateinit var calendarViewAdapter: CalenderViewAdapter
    private val viewModel by viewModels<CalenderViewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalenderViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        calendarViewAdapter = CalenderViewAdapter()
        binding.rvCalendar.apply {
            layoutManager = GridLayoutManager(requireContext(), 7)
            adapter = calendarViewAdapter
        }

        populateCalendarView()
    }

    private fun populateCalendarView() {
        calendarViewAdapter.addItems(getDummyData())
    }

    private fun getDummyData(): List<DateItem> {
        return viewModel.getCalendarDates()
    }
}