package com.nytimes.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nytimes.data.model.StoryEntity
import io.reactivex.Single

@Dao
interface StoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(story: StoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stories : List<StoryEntity>)

    @Query("SELECT * FROM story")
    fun stories(): Single<List<StoryEntity>>

    @Query("DELETE FROM story")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM story")
    fun size(): Int

    @Query("SELECT * FROM story LIMIT 1")
    fun getFirst(): StoryEntity
}