package com.example.dreamdiaryscratch.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.Instant
import java.util.*

@Parcelize
@Entity(tableName = "entry_table")
class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dreamTitle : String,
    val dreamContent: String,
    val imageResource: Int): Parcelable {
        var entryDate : String = Date.from(Instant.now()).toString()
}
