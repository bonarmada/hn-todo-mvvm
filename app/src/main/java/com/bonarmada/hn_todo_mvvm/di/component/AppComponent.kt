package com.bonarmada.hn_todo_mvvm.di.component

import com.bonarmada.hn_todo_mvvm.ui.main.MainActivity
import com.bonarmada.hn_todo_mvvm.di.module.*
import com.bonarmada.hn_todo_mvvm.di.scope.AppScope
import dagger.Component


@AppScope
@Component(modules = [AppModule::class, RemoteModule::class, NetworkModule::class,  ViewModelModule::class, RoomModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}