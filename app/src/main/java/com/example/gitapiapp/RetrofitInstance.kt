package com.example.gitapiapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        fun getRetrofitInstance():Retrofit{
            var address= Address()
            return Retrofit.Builder()
                .baseUrl(address.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}