package com.nytimes.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.nytimes.data.model.StoryEntity

@Database(
        entities = ([StoryEntity::class]),
        version = 1,
        exportSchema = false)
abstract class NYTimesDb : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory : Boolean): NYTimesDb {

            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, NYTimesDb::class.java)
            } else {
                Room.databaseBuilder(context, NYTimesDb::class.java, "ny_times.db")
            }

            return databaseBuilder.fallbackToDestructiveMigration().build()
        }
    }

    abstract fun storyDao(): StoryDao
}