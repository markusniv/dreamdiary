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

    @Delete
    suspend fun deleteEntry(entry: Entry)

    @Query("DELETE FROM entry_table")
    suspend fun deleteAllEntries()

    @Query("SELECT * FROM entry_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Entry>>
}