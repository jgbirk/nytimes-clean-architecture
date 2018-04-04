package com.nytimes.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "story")
data class StoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val summary: String,
    val url: String,
    val urlToImage: String?,
    val publishedDate: String,
    val lastUpdated: Long)