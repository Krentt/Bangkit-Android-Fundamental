package com.example.mynoteapps.helper

import java.text.SimpleDateFormat
import java.util.*

object DataHelper {
    fun getCurrentDate() : String{
        val dateFormat = SimpleDateFormat("yyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}