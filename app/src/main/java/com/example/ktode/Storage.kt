package com.example.ktode

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storage_table")
data class Storage(
    @PrimaryKey val id: Int,
    val name: String,
    val modal: Int,
    val harga: Int) {
}