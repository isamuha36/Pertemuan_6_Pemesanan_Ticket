package com.isamuha.pertemuan_6_pemesanan_ticket

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.isamuha.pertemuan_6_pemesanan_ticket.databinding.ActivityMainBinding
import com.isamuha.pertemuan_6_pemesanan_ticket.databinding.SuccessMainBinding

class SuccessActivity : AppCompatActivity(){
    private lateinit var binding: SuccessMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SuccessMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            namaYangDiinputkan.text = intent.getStringExtra("NAMA")
            jamYangDiinputkan.text = intent.getStringExtra("JAM")
            tanggalYangDiinputkan.text = intent.getStringExtra("TANGGAL")
            tujuanYangDiinputkan.text = intent.getStringExtra("TUJUAN")
        }
    }
}