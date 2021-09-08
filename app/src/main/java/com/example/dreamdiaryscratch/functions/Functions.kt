package com.example.dreamdiaryscratch.functions

import android.content.res.Resources
import android.text.TextUtils
import android.widget.RadioButton
import com.example.dreamdiaryscratch.R

fun resetMoods(button : RadioButton, resources : Resources)  {
    button.background.setTint(resources.getColor(R.color.night_blue))
}

fun inputCheck(dreamTitle : String,
                       dreamContent : String) : Boolean {
    return !(TextUtils.isEmpty(dreamTitle) && TextUtils.isEmpty(dreamContent))
}