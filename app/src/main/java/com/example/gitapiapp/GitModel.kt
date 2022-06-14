package com.example.gitapiapp

import com.google.gson.annotations.SerializedName

data class GitModel(val items:ArrayList<Items>)

data class Items(
    @SerializedName("name")
    val name:String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id:String,
    @SerializedName("html_url")
    val htmlurl: String
    )

data class Owner(
    @SerializedName("login")
    val login:String,
    @SerializedName("avatar_url")
    val avatarUrl:String)