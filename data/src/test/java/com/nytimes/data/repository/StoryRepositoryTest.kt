package com.nytimes.data.repository

import com.nytimes.data.BaseTest
import com.nytimes.data.cache.StoryCache
import com.nytimes.data.model.mapper.StoryMapper
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyList
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryRepositoryTest : BaseTest() {

    @Mock private lateinit var cache: StoryCache
    private lateinit var factory: StoryDataSourceFactory
    private lateinit var repository: StoryRepository

    @Before
    fun setUp() {
        factory = StoryDataSourceFactory(cache)
        repository = StoryRepository(factory, StoryMapper)
    }

    @Test
    fun testEmptyCacheList() {
        _when(cache.get()).thenReturn(Observable.just(listOf()))
        _when(cache.isCached()).thenReturn(Observable.just(true))
        _when(cache.isExpired()).thenReturn(Observable.just(false))

        repository.list().test().assertValue {
            verify(cache).get()

            it.isEmpty()
        }
    }

    @Test
    fun testCacheList() {
        _when(cache.get()).thenReturn(Observable.just(listOf(makeStoryEntity())))
        _when(cache.isCached()).thenReturn(Observable.just(true))
        _when(cache.isExpired()).thenReturn(Observable.just(false))

        repository.list().test().assertValue {
            verify(cache).get()

            it.isNotEmpty()
        }
    }

    @Test
    fun testExpiredCacheList() {
        _when(cache.isCached()).thenReturn(Observable.just(true))
        _when(cache.isExpired()).thenReturn(Observable.just(true))

        repository.list().test().assertValue {
            verify(cache).put(anyList())

            it.isNotEmpty()
        }
    }

    @Test
    fun testApiList() {
        _when(cache.isCached()).thenReturn(Observable.just(false))
        _when(cache.isExpired()).thenReturn(Observable.just(false))

        repository.list().test().assertValue {
            verify(cache).put(anyList())

            it.isNotEmpty()
        }
    }
}