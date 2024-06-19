package com.example.thecalendar.calendar.ui.calendarEvents

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.thecalendar.calendar.ui.viewmodels.AddEventViewModel
import com.example.thecalendar.databinding.FragmentAddEventBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class AddEventFragment : Fragment() {

    private lateinit var binding: FragmentAddEventBinding
    private val addEventViewModel by viewModels<AddEventViewModel>()
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args by navArgs<EventFragmentArgs>()
        addEventViewModel.setDate(args.timeStamp)
        initUi()
    }

    private fun initUi() {
        with(binding) {
            startTimeEditText.setOnClickListener {
                showTimePickerDialog { hour, minute -> onStartTimeSelected(hour, minute) }
            }
            endTimeEditText.setOnClickListener {
                showTimePickerDialog { hour, minute -> onEndTimeSelected(hour, minute) }
            }

            addEventButton.setOnClickListener {
                onAddBtnClick()
            }
        }
    }

    private fun onAddBtnClick() {
        with(binding) {
            val startTime = startTimeEditText.text.toString()
            val endTime = endTimeEditText.text.toString()
            val summary = summaryEditText.text.toString()

            if (startTime.isNotEmpty() && endTime.isNotEmpty() && summary.isNotEmpty()) {
                addEventViewModel.createEvent(summary)
                Toast.makeText(context, "Event Added: $summary", Toast.LENGTH_SHORT).show()
                //requireActivity().supportFragmentManager.popBackStack()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun onStartTimeSelected(hour: Int, minute: Int) {
        val time = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
        binding.startTimeEditText.setText(time)
        addEventViewModel.setStartTime(hour, minute)
    }

    private fun onEndTimeSelected(hour: Int, minute: Int) {
        val time = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
        binding.endTimeEditText.setText(time)
        addEventViewModel.setEndTime(hour, minute)
    }

    private fun showTimePickerDialog(onTimeSelected: (Int, Int) -> Unit) {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            context,
            { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                onTimeSelected(selectedHour, selectedMinute)
            },
            hour, minute, true
        )

        timePickerDialog.show()
    }
}
