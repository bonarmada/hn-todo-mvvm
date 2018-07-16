package com.bonarmada.hn_todo_mvvm.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.bonarmada.hn_todo_mvvm.data.model.Story
import retrofit2.http.DELETE

@Dao
interface StoryDao{
    @Query("SELECT * from story_table ORDER BY time ASC")
    fun getAllStories(): LiveData<List<Story>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(story:Story)

    @Delete
    fun delete(story: Story)

    @Query("DELETE from story_table")
    fun clear()
}