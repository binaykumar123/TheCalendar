package com.example.thecalendar.calendar.ui.calendarEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecalendar.calendar.ui.DateItem
import com.example.thecalendar.calendar.ui.adapters.EventAdapter
import com.example.thecalendar.calendar.ui.viewmodels.EventsViewModel
import com.example.thecalendar.databinding.FragmentEventsBinding
import com.google.api.services.calendar.model.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private lateinit var eventAdapter: EventAdapter
    private val eventsViewModel by viewModels<EventsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args by navArgs<EventFragmentArgs>()
        eventsViewModel.setDateTime(args.timeStamp)
        setUi()
        setupObserver()

    }

    private fun setUi() {
        setupEventsRecyclerView()
        binding.tvDate.text = "${eventsViewModel.date} ${eventsViewModel.month} ${eventsViewModel.year}"
    }

    private fun setupObserver() {
        eventsViewModel.eventList.observe(viewLifecycleOwner) {
            eventAdapter.addItems(it)
        }
    }

    private fun setupEventsRecyclerView() {
        eventAdapter = EventAdapter()
        with(binding.rvEvents) {
            adapter = eventAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}