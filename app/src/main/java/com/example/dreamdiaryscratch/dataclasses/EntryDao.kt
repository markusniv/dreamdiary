package com.example.dreamdiaryscratch.dataclasses

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dreamdiaryscratch.model.Entry

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEntry(entry: Entry)

    @Update
    suspend fun updateEntry(entry: Entry)

    @Query("SELECT * FROM entry_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Entry>>
}