package com.example.thecalendar.calendar.calendarView.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thecalendar.calendar.utils.uiModels.DateItem
import com.example.thecalendar.calendar.calendarView.ui.adapter.CalenderViewAdapter
import com.example.thecalendar.calendar.utils.uiModels.CalendarDataState
import com.example.thecalendar.calendar.calendarView.ui.viewmodel.CalenderViewViewModel
import com.example.thecalendar.core.utils.Constants
import com.example.thecalendar.databinding.FragmentCalenderViewBinding
import dagger.hilt.android.AndroidEntryPoint

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
            when (it) {
                is CalendarDataState.Success -> {
                    toggleLoader(false)
                    calendarViewAdapter.addItems(it.days) {
                        onDateClick(it)
                    }
                }

                is CalendarDataState.Error -> {
                    toggleLoader(false)
                    Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {

                    toggleLoader(true)
                }
            }
        }
    }

    private fun onDateClick(item: DateItem) {
        item.dateTime?.value?.let {
            val action = CalendarViewFragmentDirections.actionCalendarFragmentToEventsFragment(it)
            findNavController().navigate(action)
        }
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

        if (requestCode == Constants.RC_AUTH) {
            viewModel.fetchCalendarDates(this)
        }
    }
}