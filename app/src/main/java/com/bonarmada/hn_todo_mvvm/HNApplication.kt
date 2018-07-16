package com.bonarmada.hn_todo_mvvm

import android.app.Application
import com.bonarmada.hn_todo_mvvm.di.component.AppComponent
import com.bonarmada.hn_todo_mvvm.di.component.DaggerAppComponent
import com.bonarmada.hn_todo_mvvm.di.module.AppModule
import com.bonarmada.hn_todo_mvvm.di.module.NetworkModule
import timber.log.Timber

class HNApplication: Application(){
    val TAG = this.javaClass.simpleName
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this,this))
                .networkModule(NetworkModule(Constants.API.URL))
                .build()
    }
}