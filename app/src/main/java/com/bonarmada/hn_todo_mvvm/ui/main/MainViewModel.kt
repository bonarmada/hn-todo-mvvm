package com.bonarmada.hn_todo_mvvm.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.bonarmada.hn_todo_mvvm.data.model.Story
import com.bonarmada.hn_todo_mvvm.data.repo.StoryRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: StoryRepository) : ViewModel() {
    lateinit var liveStories: LiveData<List<Story>>
    lateinit var pagedStories: LiveData<PagedList<Story>>

    private val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(20)
            .setPrefetchDistance(5)
            .setEnablePlaceholders(false)
            .build()

    init {
        subscribe()
    }

    private fun subscribe() {
//        this.liveStories = this.repository.dao.getAllStories()
        this.pagedStories = LivePagedListBuilder(this.repository.dao.storiesByPage(), config).build()
    }

    internal fun remoteCall() = repository.getNewStories()
}