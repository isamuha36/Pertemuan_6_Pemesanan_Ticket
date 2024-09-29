package com.isamuha.pertemuan_6_pemesanan_ticket

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.isamuha.pertemuan_6_pemesanan_ticket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var kota: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kota = resources.getStringArray(R.array.tujuan)

        with(binding) {
            val adapterKota = object : ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item, kota
            ) {
                override fun isEnabled(position: Int): Boolean {
                    return position != 0
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    val view: TextView =
                        super.getDropDownView(position, convertView, parent) as TextView
                    if (position == 0) {
                        view.setTextColor(getColor(R.color.placeholder))
                    }
                    return view
                }
            }


            spinnerTujuan.adapter = adapterKota

            spinnerTujuan.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?, position: Int, id: Long
                    ) {
                        Toast.makeText(
                            this@MainActivity,
                            kota[position], Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>) {
                    }
                }

            tanggalKeberangkatan.setOnClickListener {
                val datepicker = DatePicker()
                datepicker.show(supportFragmentManager, "datepicker")
            }

            jamKeberangkatan.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "timepicker")
            }
            btnPesanTiket.setOnClickListener{
                val namaPemesan = namaPemesan.text.toString()
                val tanggalKeberangkatan = tanggalKeberangkatan.text.toString()
                val jamKeberangkatan = jamKeberangkatan.text.toString()
                val tujuan = spinnerTujuan.selectedItem.toString()

                if (namaPemesan.isEmpty() || tanggalKeberangkatan.isEmpty() || jamKeberangkatan.isEmpty() || tujuan == kota[0]) {
                    Toast.makeText(this@MainActivity, "Mohon lengkapi semua data!", Toast.LENGTH_SHORT).show()
                } else {
                    // Memunculkan NotifActivity sebagai DialogFragment
                    val notifDialog = NotifActivity.newInstance(
                        namaPemesan, tanggalKeberangkatan, jamKeberangkatan, tujuan
                    )
                    notifDialog.show(supportFragmentManager, "notifDialog")
                }
            }
        }
    }

    override fun onDateSet(
        p0: android.widget.DatePicker?,
        p1: Int, p2: Int, p3: Int) {
        val tanggalYangDipilih = "$p3/${p2 + 1}/$p1"
        binding.tanggalKeberangkatan.text = tanggalYangDipilih
    }

    override fun onTimeSet(
        p0: TimePicker?,
        p1: Int, p2: Int) {
        val selectedTime = String.format("%02d:%02d", p1, p2)
        binding.jamKeberangkatan.text = selectedTime
    }


}