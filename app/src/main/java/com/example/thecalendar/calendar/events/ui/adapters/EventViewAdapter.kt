package com.example.thecalendar.calendar.events.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thecalendar.calendar.utils.uiModels.CalendarEvent
import com.example.thecalendar.core.utils.DateUtils
import com.example.thecalendar.databinding.ItemEventBinding

class EventAdapter() :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var events: MutableList<CalendarEvent> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    fun addItems(items: List<CalendarEvent>) {
        events.clear()
        events.addAll(items)
        notifyDataSetChanged()
    }

    class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: CalendarEvent) {
            val startTime = DateUtils.convertDateTimeToTimeString(event.startTime)
            val endTime = DateUtils.convertDateTimeToTimeString(event.endTime)
            binding.apply {
                tvTitle.text = event.summary
                tvDate.text = DateUtils.convertDateTimeToDateString(event.startTime)
                "$startTime - $endTime".also { tvTime.text = it }
                tvDescription.text = event.description
            }
        }
    }
}
