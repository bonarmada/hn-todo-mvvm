package com.bonarmada.hn_todo_mvvm.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bonarmada.recipes.viewmodel.ViewModelFactory
import javax.inject.Inject

open class BaseActivity: AppCompatActivity(){
    @Inject
    lateinit var factory: ViewModelFactory

}