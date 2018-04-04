package com.nytimes.domain.interactor

import com.nytimes.domain.executor.PostExecutionThread
import com.nytimes.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when` as _when
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UseCaseTest {

    @Mock private lateinit var executorThread: ThreadExecutor
    @Mock private lateinit var postExecutionThread: PostExecutionThread
    private lateinit var useCase: UseCaseTestClass
    private lateinit var testObserver: TestDisposableObserver<Any>

    @Before
    fun setUp() {
        _when(postExecutionThread.scheduler).thenReturn(TestScheduler())

        useCase = UseCaseTestClass(executorThread, postExecutionThread)
        testObserver = TestDisposableObserver()
    }

    @Test
    fun testBuildObservable() {
        useCase.execute(testObserver)

        assert(testObserver.valuesCount == 0)
    }

    @Test
    fun testSubscription() {
        useCase.execute(testObserver)
        useCase.dispose()

        assert(testObserver.isDisposed)
    }

    private class UseCaseTestClass constructor(
        executorThread: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ) : UseCase<Any, Any>(executorThread, postExecutionThread) {
        override fun buildObservable(params: Any?): Observable<Any> {
            return Observable.empty()
        }
    }

    private class TestDisposableObserver<T> : DisposableObserver<T>() {
        var valuesCount = 0

        override fun onNext(value: T) {
            valuesCount++
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }
    }
}