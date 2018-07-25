package com.bonarmada.hn_todo_mvvm.ui.base

import android.support.v4.app.Fragment
import com.bonarmada.recipes.viewmodel.ViewModelFactory
import javax.inject.Inject

open class BaseFragment: Fragment(){
    @Inject
    lateinit var factory: ViewModelFactory
}