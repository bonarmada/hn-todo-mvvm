package com.bonarmada.hn_todo_mvvm.di.component

import com.bonarmada.hn_todo_mvvm.MainActivity
import com.bonarmada.hn_todo_mvvm.di.module.ContextModule
import com.bonarmada.hn_todo_mvvm.di.module.ViewModelModule
import com.bonarmada.hn_todo_mvvm.di.scope.AppScope
import dagger.Component


@AppScope
@Component(modules = [ContextModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}