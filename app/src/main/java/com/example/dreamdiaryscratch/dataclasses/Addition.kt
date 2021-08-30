package com.example.dreamdiaryscratch.dataclasses

import java.time.Instant
import java.util.*

data class Dream(val type : String = "regular",
                 val content : String = "no information")

class Addition(val dream : Dream) {
    val additionDate : Date = Date.from(Instant.now())
}