package com.example.dreamdiaryscratch.dataclasses

import androidx.lifecycle.LiveData

class EntryRepository(private val entryDao: EntryDao) {
    val readAllData: LiveData<List<Entry>> = entryDao.readAllData()

    suspend fun addEntry(entry: Entry) {
        entryDao.addEntry(entry)
    }
}