package com.nytimes.domain.interactor

import com.nytimes.domain.executor.PostExecutionThread
import com.nytimes.domain.executor.ThreadExecutor
import com.nytimes.domain.model.Story
import com.nytimes.domain.repository.Repository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetStoryListTest {

    @Mock private lateinit var repository: Repository<Story>
    @Mock private lateinit var executorThread: ThreadExecutor
    @Mock private lateinit var postExecutionThread: PostExecutionThread
    private lateinit var useCase: GetStoryList

    @Before
    fun setUp() {
        useCase = GetStoryList(repository, executorThread, postExecutionThread)
    }

    @Test
    fun testList() {
        useCase.buildObservable()
        verify(repository).list()
    }
}