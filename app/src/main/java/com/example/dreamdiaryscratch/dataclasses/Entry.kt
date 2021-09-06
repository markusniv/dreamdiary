package com.example.dreamdiaryscratch.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "entry_table")
class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dreamTitle : String,
    val dreamContent: String,
    val imageResource: Int) {
        var entryDate : String = Date.from(Instant.now()).toString()
}
