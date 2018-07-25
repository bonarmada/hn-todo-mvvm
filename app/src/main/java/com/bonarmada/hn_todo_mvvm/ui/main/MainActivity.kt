package com.bonarmada.hn_todo_mvvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bonarmada.hn_todo_mvvm.HNApplication
import com.bonarmada.hn_todo_mvvm.R
import com.bonarmada.hn_todo_mvvm.data.model.Story
import com.bonarmada.hn_todo_mvvm.data.vo.StoryItem
import com.bonarmada.hn_todo_mvvm.ui.base.BaseActivity
import com.bonarmada.hn_todo_mvvm.util.PagedListGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private var stories: List<Story> = ArrayList()
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GroupAdapter<ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as HNApplication).component.inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        adapter = GroupAdapter()
    }

    override fun onStart() {
        super.onStart()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

//        viewModel.pagedStories.observe(this, Observer {
//            it?.forEach {
//                adapter.add()
//            }
//        })

        viewModel.liveStories.observe(this, Observer {
            it?.let{adapter.update(it)}
        })
    }


}