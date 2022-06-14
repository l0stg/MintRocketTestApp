package com.example.gitapiapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: GitListAdapter
    lateinit var gitViewModel: GitViewModel
    lateinit var refreshLayout: SwipeRefreshLayout
    var page: Int=2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refreshLayout=findViewById(R.id.swipeRefreshLayout)
        initRecyclerView()
        createData()
        refreshLayout.setOnRefreshListener{pullToRefresh()}
    }

    fun pullToRefresh(){
        myAdapter.clearData()
        myAdapter.notifyDataSetChanged()
        gitViewModel.getRepos(1)
        refreshLayout.isRefreshing=false

    }

    fun initRecyclerView(){
        recyclerView.apply {
            layoutManager=LinearLayoutManager(applicationContext)
            myAdapter= GitListAdapter()
            adapter=myAdapter
            pagination(layoutManager as LinearLayoutManager,recyclerView)

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }
    fun pagination(mLayoutManager:LinearLayoutManager,mRecyclerView:RecyclerView){
        var loading = true
        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount()
                    totalItemCount = mLayoutManager.getItemCount()
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            gitViewModel.getRepos(page++)
                            loading = true
                        }
                    }
                }
            }
        })
    }

    fun createData(){
        gitViewModel=ViewModelProvider(this).get(GitViewModel::class.java)
        gitViewModel.getRecyclerListDataObserver().observe(this, Observer<GitModel> {
            if(it !=null){
                myAdapter.setData(it.items)
                myAdapter.notifyDataSetChanged()

            }else{
                Log.e("Error","Ошибка")

            }
        })
        gitViewModel.getRepos()
    }
}