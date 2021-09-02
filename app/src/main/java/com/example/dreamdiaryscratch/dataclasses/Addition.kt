package com.example.dreamdiaryscratch.dataclasses

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

class Addition(val dream : Dream, val imageResource: Int) {
    val additionDate : Date = Date.from(Instant.now())
    val dreamTypeToString : String
        get() = when(dream.type) {
        DreamType.LUCID_DREAM -> "lucid dream"
        DreamType.NIGHTMARE -> "nightmare"
        else -> "regular"
    }

    override fun toString(): String {
        return "Dream type: ${dream.type} | Dream content: ${dream.content}"
    }
}