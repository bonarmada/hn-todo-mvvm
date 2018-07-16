package com.bonarmada.hn_todo_mvvm.di.module

import com.bonarmada.hn_todo_mvvm.data.StoryRemote
import com.bonarmada.hn_todo_mvvm.di.scope.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class RemoteModule{
    @AppScope
    @Provides
    internal fun provideStoryRemote(retrofit: Retrofit): StoryRemote = retrofit.create(StoryRemote::class.java)
}