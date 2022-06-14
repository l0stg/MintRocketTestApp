package com.example.gitapiapp

import com.google.gson.annotations.SerializedName

data class GitModel(val items:ArrayList<Items>)

data class Items(
    val name:String,
    val owner: Owner,
    val description: String,
    val stargazers_count: Int,
    val id:String,
    @SerializedName("html_url")
    val htmlurl: String
    )

data class Owner(val login:String, val avatar_url:String)