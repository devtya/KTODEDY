package com.example.ktode

import androidx.room.*

@Dao
interface StorageDao {
    @Query("SELECT * FROM storage_table")
    fun getAll(): List<Storage>

    @Insert
    fun insertAll(vararg storage: Storage)

    @Delete
    fun delete(storage: Storage)

    @Update
    fun update(vararg storage: Storage)
}