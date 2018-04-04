package com.nytimes.data.repository

import com.nytimes.data.cache.StoryCache
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryApiDataSourceTest {

    @Mock private lateinit var cache: StoryCache
    private lateinit var apiDataSource: StoryApiDataSource

    @Test
    fun testList() {
        apiDataSource = StoryApiDataSource(cache)

        apiDataSource.list().test().assertValue {
            verify(cache).put(it)

            it.isNotEmpty()
        }
    }
}