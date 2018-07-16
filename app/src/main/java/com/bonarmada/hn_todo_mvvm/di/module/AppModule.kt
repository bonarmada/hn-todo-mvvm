package com.bonarmada.hn_todo_mvvm.di.module

import android.app.Application
import android.content.Context
import com.bonarmada.hn_todo_mvvm.HNApplication
import com.bonarmada.hn_todo_mvvm.di.scope.AppScope
import dagger.Module
import dagger.Provides


@Module
class AppModule constructor(private val app: HNApplication, private val context: Context){

    @AppScope
    @Provides
    internal fun provideApplication(): HNApplication = app

    @AppScope
    @Provides
    internal fun provideContext(): Context = context
}