package com.example.ktode

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Storage::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun storageDao(): StorageDao
}