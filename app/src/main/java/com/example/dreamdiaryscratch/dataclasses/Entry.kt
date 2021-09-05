package com.example.dreamdiaryscratch.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

data class Dream(var type : DreamType = DreamType.REGULAR,
                 var title : String = "unnamed",
                 var content : String = "no information")

enum class DreamType {
    REGULAR,
    LUCID_DREAM,
    NIGHTMARE
}

@Entity(tableName = "entry_table")
class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dreamType : DreamType,
    val dreamTitle : String,
    val dreamContent: String,
    val imageResource: Int) {
        var entryDate : String = Date.from(Instant.now()).toString()
        val dreamTypeToString : String
            get() = when(dreamType) {
            DreamType.LUCID_DREAM -> "lucid dream"
            DreamType.NIGHTMARE -> "nightmare"
            else -> "regular"
    }
}