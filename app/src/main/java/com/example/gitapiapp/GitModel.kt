package com.example.gitapiapp

data class GitModel (val items:ArrayList<Items>)

data class Items(
    val name:String,
    val owner: Owner,
    val description: String,
    val stargazers_count: Int,
    val id:String,
    val html_url: String
    )

data class Owner(val login:String, val avatar_url:String)