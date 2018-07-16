package com.bonarmada.hn_todo_mvvm.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.bonarmada.hn_todo_mvvm.data.model.Story
import com.bonarmada.hn_todo_mvvm.data.repo.StoryRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: StoryRepository) : ViewModel(){
    lateinit var storiesLiveData: LiveData<List<Story>>

    init {
        subscribe()
    }
    private fun subscribe(){
        this.storiesLiveData = this.repository.dao.getAllStories()
    }

    internal fun remoteCall() = repository.getNewStories()
}