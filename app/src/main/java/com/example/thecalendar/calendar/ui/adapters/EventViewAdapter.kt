package com.example.thecalendar.calendar.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thecalendar.core.utils.DateUtils
import com.example.thecalendar.databinding.ItemEventBinding
import com.google.api.services.calendar.model.Event

class EventAdapter() :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var events: MutableList<Event> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    fun addItems(items: List<Event>) {
        events.clear()
        events.addAll(items)
        notifyDataSetChanged()
    }

    class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            val startTime = DateUtils.convertDateTimeToTimeString(event.start.dateTime)
            val endTime = DateUtils.convertDateTimeToTimeString(event.end.dateTime)
            binding.apply {
                tvTitle.text = event.summary
                tvDate.text = DateUtils.convertDateTimeToDateString(event.start.dateTime)
                "$startTime - $endTime".also { tvTime.text = it }
                tvDescription.text = event.description
            }
        }
    }
}
