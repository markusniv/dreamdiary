package com.example.dreamdiaryscratch.dataclasses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEntry(entry: Entry)

    @Query("SELECT * FROM entry_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Entry>>
}