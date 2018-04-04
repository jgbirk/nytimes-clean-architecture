package com.nytimes.data.repository

import com.nytimes.data.BaseTest
import com.nytimes.data.cache.StoryCache
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryDbDataSourceTest : BaseTest() {

    @Mock
    private lateinit var cache: StoryCache
    private lateinit var dbDataSource: StoryDbDataSource

    @Test
    fun testEmptyList() {
        dbDataSource = StoryDbDataSource(cache)

        _when(cache.get()).thenReturn(Observable.just(listOf()))

        dbDataSource.list().test().assertValue {
            verify(cache).get()

            it.isEmpty()
        }
    }

    @Test
    fun testNotEmptyList() {
        dbDataSource = StoryDbDataSource(cache)

        _when(cache.get()).thenReturn(Observable.just(listOf(makeStoryEntity())))

        dbDataSource.list().test().assertValue {
            verify(cache).get()

            it.isNotEmpty()
        }
    }
}