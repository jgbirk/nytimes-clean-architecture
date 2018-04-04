package com.nytimes.data.cache

import com.nytimes.data.BaseTest
import com.nytimes.data.db.NYTimesDb
import com.nytimes.data.db.StoryDao
import com.nytimes.data.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when` as _when
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryCacheTest : BaseTest() {

    @Mock private lateinit var database: NYTimesDb
    @Mock private lateinit var storyDao: StoryDao
    private lateinit var cache: StoryCache

    @Test
    fun testHasNotExpired() {
        cache = StoryCache(mock())
        cache.database = database

        val storyEntity = makeStoryEntity(System.currentTimeMillis())

        _when(database.storyDao()).thenReturn(storyDao)
        _when(storyDao.size()).thenReturn(1)
        _when(storyDao.getFirst()).thenReturn(storyEntity)

        cache.isExpired().test().assertValue(false)
        cache.isCached().test().assertValue(true)
    }

    @Test
    fun testHasExpired() {
        cache = StoryCache(mock())
        cache.database = database

        val storyEntity = makeStoryEntity(System.currentTimeMillis() - (2 * StoryCache.EXPIRATION_TIME))

        _when(database.storyDao()).thenReturn(storyDao)
        _when(storyDao.size()).thenReturn(1)
        _when(storyDao.getFirst()).thenReturn(storyEntity)

        cache.isExpired().test().assertValue(true)
        cache.isCached().test().assertValue(true)
    }

    @Test
    fun testCacheIsEmpty() {
        cache = StoryCache(mock())
        cache.database = database

        _when(database.storyDao()).thenReturn(storyDao)
        _when(storyDao.size()).thenReturn(0)

        cache.isExpired().test().assertValue(false)
        cache.isCached().test().assertValue(false)
    }
}