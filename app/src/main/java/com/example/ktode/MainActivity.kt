package com.example.ktode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.ktode.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var  storages : List<Storage>
    private lateinit var storageAdapter: StorageAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db: AppDatabase
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storages = arrayListOf()

        storageAdapter = StorageAdapter(storages)
        linearLayoutManager = LinearLayoutManager(this)
        db = Room.databaseBuilder(this,AppDatabase::class.java,"storage_table").build()

        binding.recyclerView.apply {
            adapter = storageAdapter
            layoutManager = linearLayoutManager
        }

        DividerItemDecoration(
            this, // context
            linearLayoutManager.orientation
        ).apply {
            // add divider item decoration to recycler view
            // this will show divider line between items
            binding.recyclerView.addItemDecoration(this)
        }

        binding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tambahBarang -> {
                    Intent(this,AddStorageActivity::class.java).also {
                        startActivity(it)
                    }
                    true
                }
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.tansaction -> {

                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }

    }

    private fun fetchAll() {
        GlobalScope.launch {
            storages = db.storageDao().getAll()
            runOnUiThread {
                storageAdapter.setData(storages)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchAll()
    }
}