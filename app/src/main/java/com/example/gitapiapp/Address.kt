package com.example.gitapiapp

import java.text.SimpleDateFormat
import java.util.*

class Address{
    val past30days:String
        init{
            val calendar=Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -30)
            val formatter=SimpleDateFormat("yyyy-MM-dd")
            past30days=formatter.format(calendar.time)
        }

    val baseUrl:String="https://api.github.com/search/"
    val q:String="created:>$past30days"
    val sort:String="stars"
    val order:String="desc"
}