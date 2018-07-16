package com.bonarmada.hn_todo_mvvm.data

import android.support.annotation.IntegerRes
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface StoryRemote{
    @GET("topstories.json")
    fun getTop(): Single<Response<List<Int>>>

    @GET("newstories.json")
    fun getNew(): Single<Response<List<Int>>>
}