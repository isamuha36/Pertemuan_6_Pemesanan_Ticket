package com.isamuha.pertemuan_6_pemesanan_ticket

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.isamuha.pertemuan_6_pemesanan_ticket.databinding.ActivityMainBinding
import com.isamuha.pertemuan_6_pemesanan_ticket.databinding.NotifMainBinding

class NotifActivity : DialogFragment() {
    private lateinit var binding: NotifMainBinding

    companion object {
        fun newInstance(nama: String, tanggal: String, jam: String, tujuan: String): NotifActivity {
            val args = Bundle().apply {
                putString("NAMA", nama)
                putString("TANGGAL", tanggal)
                putString("JAM", jam)
                putString("TUJUAN", tujuan)
            }
            val fragment = NotifActivity()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        binding = NotifMainBinding.inflate(layoutInflater)
        builder.setView(binding.root)

        val namaPemesan = arguments?.getString("NAMA")
        val tanggalKeberangkatan = arguments?.getString("TANGGAL")
        val jamKeberangkatan = arguments?.getString("JAM")
        val tujuan = arguments?.getString("TUJUAN")

        binding.btnNo.setOnClickListener{
            dismiss()
        }

        binding.btnYes.setOnClickListener{
            val intent = Intent(requireActivity(), SuccessActivity::class.java).apply {
                putExtra("NAMA", namaPemesan)
                putExtra("TANGGAL", tanggalKeberangkatan)
                putExtra("JAM", jamKeberangkatan)
                putExtra("TUJUAN", tujuan)
            }
            startActivity(intent)
            dismiss()
        }

        return builder.create()
    }
}