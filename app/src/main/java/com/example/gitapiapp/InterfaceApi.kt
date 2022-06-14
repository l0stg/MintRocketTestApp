package com.example.gitapiapp


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceApi {
    @GET("repositories")
    fun ApiGetReposResult(
        @Query("q") q:String?,
        @Query("sort") sortWith:String?,
        @Query("order") order:String?,
        @Query("page") page:Int?
    ):Call<GitModel>
}