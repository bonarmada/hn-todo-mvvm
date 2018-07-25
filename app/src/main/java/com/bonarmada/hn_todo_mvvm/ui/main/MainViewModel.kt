package com.bonarmada.hn_todo_mvvm.ui.main

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.bonarmada.hn_todo_mvvm.data.model.Story
import com.bonarmada.hn_todo_mvvm.data.repo.StoryRepository
import com.bonarmada.hn_todo_mvvm.data.vo.StoryItem
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: StoryRepository) : ViewModel() {
    lateinit var liveStories: LiveData<List<StoryItem>>
    lateinit var pagedStories: LiveData<PagedList<Story>>
    lateinit var pagedStoryItem: LiveData<PagedList<StoryItem>>

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
        this.pagedStories = LivePagedListBuilder(this.repository.dao.storiesByPage(), config).build()

        this.liveStories = Transformations.map(this.repository.dao.getAllStories(), Function {
            ArrayList<StoryItem>().apply {
                it.forEach {
                    this.add(StoryItem(it))
                }
            }
        })
    }

    internal fun refreshNewStories() = repository.fetchNewFromRemote()
    internal fun refreshStory(itemId: Int) = repository.fetchStoryFromRemote(itemId)
}
