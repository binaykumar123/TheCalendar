package com.example.thecalendar.calendar.events.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecalendar.calendar.events.ui.adapters.EventAdapter
import com.example.thecalendar.calendar.events.ui.viewmodel.EventsViewModel
import com.example.thecalendar.databinding.FragmentEventsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private lateinit var eventAdapter: EventAdapter
    private val eventsViewModel by viewModels<EventsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args by navArgs<EventFragmentArgs>()
        eventsViewModel.setDateTime(args.timeStamp)
        setUi()
        setupObserver()
        eventsViewModel.fetchEvents()
    }

    override fun onStart() {
        super.onStart()
        eventsViewModel.fetchEvents()
    }

    private fun setUi() {
        setupEventsRecyclerView()
        binding.tvDate.text = eventsViewModel.dateString
        binding.btnAddEvent.setOnClickListener {
            navigateToAddFragment()
        }
    }

    private fun navigateToAddFragment() {
        val action =
            EventFragmentDirections.actionEventsFragmentToAddEventFragment(eventsViewModel.dateTimeStamp)
        findNavController().navigate(action)
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