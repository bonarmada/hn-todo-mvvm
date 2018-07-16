package com.bonarmada.hn_todo_mvvm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bonarmada.hn_todo_mvvm.data.StoryRemote
import com.bonarmada.hn_todo_mvvm.data.repo.StoryRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var repo: StoryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as HNApplication).component.inject(this)
    }

    override fun onStart() {
        super.onStart()

        repo.getNewStories()
    }
}
