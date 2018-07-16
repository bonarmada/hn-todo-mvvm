package com.bonarmada.hn_todo_mvvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bonarmada.hn_todo_mvvm.HNApplication
import com.bonarmada.hn_todo_mvvm.R
import com.bonarmada.hn_todo_mvvm.data.repo.StoryRepository
import com.bonarmada.hn_todo_mvvm.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {
    private val tag = this::class.java.simpleName

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as HNApplication).component.inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        viewModel.storiesLiveData.observe(this, Observer {stories ->
            stories?.forEach {
                Log.d(tag, it.toString())
            }
        })

        
    }
}
