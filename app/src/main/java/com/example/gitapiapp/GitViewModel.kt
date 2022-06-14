package com.example.gitapiapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitViewModel(application: Application):AndroidViewModel(application){
    lateinit var mutableLiveData: MutableLiveData<GitModel>
    init {
        mutableLiveData= MutableLiveData()
    }
    fun getRecyclerListDataObserver():MutableLiveData<GitModel>{
        return mutableLiveData
    }
    fun getRepos(page:Int=1){
        val address= Address()
        val retrofitInstance= RetrofitInstance.getRetrofitInstance().create(InterfaceApi::class.java)
        val call=retrofitInstance.ApiGetReposResult(address.q,address.sort,address.order,page)

        call.enqueue(object :Callback<GitModel>{
            override fun onResponse(call: Call<GitModel>, response: Response<GitModel>){
                Log.d("ТЭГС","Получили ответ от айпи")
                mutableLiveData.postValue(response.body())

            }
            override fun onFailure(call: Call<GitModel>, t: Throwable){
                Log.e("ТЭГС","ОШИБКА")
            }

        })
    }

}