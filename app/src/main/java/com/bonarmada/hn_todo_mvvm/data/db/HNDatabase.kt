package com.bonarmada.hn_todo_mvvm.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.bonarmada.hn_todo_mvvm.data.model.Story

@Database(entities = [Story::class], version = 1)
abstract class HNDatabase: RoomDatabase(){
    abstract fun storyDao(): StoryDao
}