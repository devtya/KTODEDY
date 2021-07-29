package com.example.ktode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.*

class StorageAdapter(private var storages: List<Storage>): RecyclerView.Adapter<StorageAdapter.StorageHolder>() {
    class StorageHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val harga: TextView = view.findViewById(R.id.tvHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.storage_layout,parent,false)
        return StorageHolder(view)
    }

    override fun onBindViewHolder(holder: StorageHolder, position: Int) {
        val storage = storages[position]
        holder.name.text = storage.name
        holder.harga.text = storage.harga.convertRupiah()
    }

    override fun getItemCount(): Int {
        return storages.size
    }

    fun Any.convertRupiah(): String {
        val localId = Locale("in","ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }
    fun setData(storages: List<Storage>){
        this.storages = storages
        notifyDataSetChanged()
    }
}