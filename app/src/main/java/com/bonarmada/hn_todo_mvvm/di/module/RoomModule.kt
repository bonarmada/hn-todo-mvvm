package com.bonarmada.hn_todo_mvvm.di.module

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import com.bonarmada.hn_todo_mvvm.HNApplication
import com.bonarmada.hn_todo_mvvm.data.db.HNDatabase
import com.bonarmada.hn_todo_mvvm.di.scope.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class RoomModule{

    @AppScope
    @Provides
    fun provideHnDatabase(app: HNApplication): HNDatabase = Room.databaseBuilder(app,
            HNDatabase::class.java, "hn-todo.db").build()

    @AppScope
    @Provides
    fun provideStoryDao(db: HNDatabase) = db.storyDao()
}