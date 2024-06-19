package com.example.thecalendar.calendar.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.thecalendar.R
import com.example.thecalendar.calendar.ui.DateItem
import com.example.thecalendar.core.utils.DateUtils
import com.example.thecalendar.databinding.ItemDateBinding


class CalenderViewAdapter(private val items: MutableList<DateItem> = mutableListOf()) :
    RecyclerView.Adapter<CalenderViewAdapter.DateViewHolder>() {

    private var onClick: ((DateItem) -> Unit)? = null

    class DateViewHolder(private val binding: ItemDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DateItem, onItemClick: ((DateItem) -> Unit)?) {
            binding.apply {
                if (item.isDummyDay) {
                    tvDate.isVisible = false
                    tvEvents.isVisible = false
                } else {
                    if (DateUtils.isCurrentDay(item.dateTime?.value)) {
                        bgCurrentDay.setImageResource(R.drawable.bg_cuurent_day)
                    } else {
                        bgCurrentDay.setImageResource(R.color.white)
                    }
                    tvDate.text = "${item.displayDate}"
                    tvEvents.isVisible = item.events?.isNotEmpty() ?: false
                    root.setOnClickListener {
                        onItemClick?.invoke(item)
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val binding =
            ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(items[position]) { date ->
            onClick?.invoke(date)
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(dates: List<DateItem>, onItemClick: ((DateItem) -> Unit)? = null) {
        onClick = onItemClick
        items.clear()
        items.addAll(dates)
        notifyDataSetChanged()
    }
}