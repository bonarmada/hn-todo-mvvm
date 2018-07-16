package com.bonarmada.hn_todo_mvvm.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "story_table")
data class Story(
        @PrimaryKey @SerializedName("id") var id: Int,
        @SerializedName("type") var type: String,
        @SerializedName("by") var by: String? = null,
        @SerializedName("descendants") var descendants: Int? = null,
//        @SerializedName("kids") var kids: List<Int>?,
        @SerializedName("score") var score: Int? = null,
        @SerializedName("time") var time: Int? = null,
        @SerializedName("title") var title: String? =null,
        @SerializedName("url") var url: String?=null )