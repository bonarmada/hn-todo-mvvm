package com.bonarmada.hn_todo_mvvm.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bonarmada.hn_todo_mvvm.di.ViewModelKey
import com.bonarmada.hn_todo_mvvm.di.scope.AppScope
import com.bonarmada.hn_todo_mvvm.ui.main.MainViewModel
import com.bonarmada.recipes.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @AppScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    // Factory
    @AppScope
    @Binds
    abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory
}
