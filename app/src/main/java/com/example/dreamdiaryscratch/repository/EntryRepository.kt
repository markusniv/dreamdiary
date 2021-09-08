package com.example.dreamdiaryscratch.repository

import androidx.lifecycle.LiveData
import com.example.dreamdiaryscratch.dataclasses.EntryDao
import com.example.dreamdiaryscratch.model.Entry

class EntryRepository(private val entryDao: EntryDao) {
    val readAllData: LiveData<List<Entry>> = entryDao.readAllData()

    suspend fun addEntry(entry: Entry) {
        entryDao.addEntry(entry)
    }

    suspend fun updateEntry(entry: Entry) {
        entryDao.updateEntry(entry)
    }
}