package com.bonarmada.hn_todo_mvvm.data

import android.support.annotation.IntegerRes
import com.bonarmada.hn_todo_mvvm.data.model.Story
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoryRemote{
    @GET("topstories.json")
    fun getTop(): Single<Response<List<Int>>>

    @GET("newstories.json")
    fun getNew(): Single<Response<List<Int>>>

    @GET("item/{id}.json")
    fun getStory(@Path("id") id:Int): Single<Response<Story>>
}