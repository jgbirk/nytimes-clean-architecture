package com.nytimes.data.db

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nytimes.data.model.StoryEntity
import org.junit.Rule
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StoryDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: NYTimesDb
    private lateinit var storyDao: StoryDao

    @Before
    @Throws(Exception::class)
    fun init() {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                NYTimesDb::class.java)
                .allowMainThreadQueries()
                .build()

        storyDao = database.storyDao()
    }

    @After
    @Throws(Exception::class)
    fun close() {
        database.close()
    }

    @Test
    fun testInsert() {
        storyDao.insert(makeStory(1))
        storyDao.stories().test().assertValueCount(1)
    }

    @Test
    fun testInsertAll() {
        val list = listOf(makeStory(1), makeStory(2))

        storyDao.insert(list)
        storyDao.stories().test().assertValue(list)
    }

    @Test
    fun testDeleteAll() {
        val list = listOf(makeStory(1), makeStory(2))

        storyDao.insert(list)

        assert(storyDao.size() == 2)

        storyDao.deleteAll()

        assert(storyDao.size() == 0)
    }

    @Test
    fun testGetFirst() {
        storyDao.insert(makeStory(1))

        assert(storyDao.getFirst().id == 1)
    }

    private fun makeStory(id: Int) = StoryEntity(
        id,
        "title",
        "summary",
        "url",
        "urlToImage",
        "publishedDate",
        System.currentTimeMillis()
    )
}
