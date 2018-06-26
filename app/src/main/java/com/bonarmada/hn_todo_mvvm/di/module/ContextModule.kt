package com.bonarmada.hn_todo_mvvm.di.module

import android.content.Context
import com.bonarmada.hn_todo_mvvm.di.scope.AppScope
import dagger.Module
import dagger.Provides


@Module
class ContextModule constructor(private val context: Context){

    @AppScope
    @Provides
    internal fun provideContext(): Context = context
}