package com.bonarmada.hn_todo_mvvm.data.repo

import android.arch.paging.DataSource
import android.util.Log
import com.bonarmada.hn_todo_mvvm.data.StoryRemote
import com.bonarmada.hn_todo_mvvm.data.db.StoryDao
import com.bonarmada.hn_todo_mvvm.data.model.Story
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StoryRepository @Inject internal constructor(private val remote: StoryRemote, internal val dao: StoryDao) {
    private val tag = this::class.java.simpleName

    fun getNewStories(){
        remote.getNew()
                .subscribeOn(Schedulers.io())
                .subscribe { res, ex ->
                    // TODO: do something
                    if (res.code() == 200){
                        res.body()?.forEach {
                            dao.insert(Story(it, "test data"))
                        }
                    }
                }
    }

}