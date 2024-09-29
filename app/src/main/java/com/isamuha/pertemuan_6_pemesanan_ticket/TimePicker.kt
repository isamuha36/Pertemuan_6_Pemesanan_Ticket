package com.isamuha.pertemuan_6_pemesanan_ticket

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.text.DateFormat
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import kotlin.math.min

class TimePicker : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            requireActivity(),
            R.style.CustomDialog,
            activity as TimePickerDialog.OnTimeSetListener,
            hourOfDay,
            minute,
            android.text.format.DateFormat.is24HourFormat(activity)
        )
    }
}