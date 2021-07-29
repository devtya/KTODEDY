package com.example.ktode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.example.ktode.databinding.ActivityAddStorageBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddStorageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStorageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputId.addTextChangedListener {
            if (it!!.count()>0)
                binding.layoutId.error = null
        }
        binding.inputName.addTextChangedListener {
            if (it!!.count()>0)
                binding.layoutName.error = null
        }
        binding.inputModal.addTextChangedListener {
            if (it!!.count()>0)
                binding.layoutModal.error = null
        }
        binding.inputHarga.addTextChangedListener {
            if (it!!.count()>0)
                binding.layoutHarga.error = null
        }

        binding.btnAddBarang.setOnClickListener {
            val id = binding.inputId.text.toString().toIntOrNull()
            val name = binding.inputName.text.toString()
            val modal = binding.inputModal.text.toString().toIntOrNull()
            val harga = binding.inputHarga.text.toString().toIntOrNull()

            if (id == null)
                binding.layoutId.error = "Kode barcode tidak boleh kosong"

            else if (name.isEmpty())
                binding.layoutName.error = "Nama barang tidak boleh kosong"

            else if (modal == null)
                binding.inputModal.error = "Modal tidak bileh kosong"

            else if (harga == null)
                binding.inputHarga.error = "Harga tidak boleh kosong"
            else {
                val storage = Storage(id,name,modal, harga)
                insert(storage)
            }

        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
    private fun insert(storage: Storage){
        val db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "storage_table").build()

        GlobalScope.launch {
            db.storageDao().insertAll(storage)
            finish()
        }
    }
}