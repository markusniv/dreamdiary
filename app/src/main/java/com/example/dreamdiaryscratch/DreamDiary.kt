package com.example.dreamdiaryscratch

import android.app.Application
import android.content.Context

class DreamDiary : Application() {

    companion object {
        var ctx: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
    }
}