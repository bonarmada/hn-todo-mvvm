package com.bonarmada.hn_todo_mvvm.data.repo

import android.util.Log
import com.bonarmada.hn_todo_mvvm.data.StoryRemote
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StoryRepository @Inject internal constructor(private val remote: StoryRemote) {
    private val tag = this::class.java.simpleName

    fun getNewStories(){
        remote.getNew()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { res, ex ->
                    // TODO: do something
                }
    }
}