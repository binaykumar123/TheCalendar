package com.example.thecalendar.calendar.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thecalendar.calendar.ui.DateItem
import com.example.thecalendar.databinding.ItemDateBinding


class CalenderViewAdapter(private val items: MutableList<DateItem> = mutableListOf()) :
    RecyclerView.Adapter<CalenderViewAdapter.DateViewHolder>() {

    private var onClick: ((Long) -> Unit)? = null

    class DateViewHolder(private val binding: ItemDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DateItem, onItemClick: ((Long) -> Unit)?) {
            binding.apply {
                tvDate.text = item.text
                root.setOnClickListener { /*onItemClick?.invoke(item.id)*/ }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val binding =
            ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(items[position]) { id ->
            onClick?.invoke(id)
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(dates: List<DateItem>, onItemClick: ((Long) -> Unit)? = null) {
        onClick = onItemClick
        items.clear()
        items.addAll(dates)
        notifyDataSetChanged()
    }
}