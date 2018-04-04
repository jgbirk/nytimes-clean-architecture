package com.nytimes.presentation.view.fragment

import com.nytimes.data.cache.StoryCache
import com.nytimes.data.repository.StoryRepository
import com.nytimes.presentation.BaseTest
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.`when` as _when
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryListPresenterTest : BaseTest() {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            RxAndroidPlugins.reset()
        }
    }

    @Mock private lateinit var cache: StoryCache
    private lateinit var presenter: StoryListPresenter
    @Mock private lateinit var view: StoryListContract.View
    @Mock private lateinit var repository: StoryRepository

    @Before
    fun setUp() {
        _when(repository.list()).thenReturn(Observable.just(listOf(makeStory())))

        presenter = StoryListPresenter(cache, repository)
        presenter.view = view
    }

    @Test
    fun testLoadStories() {
        presenter.loadStories()

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showStories(anyList())
    }
}