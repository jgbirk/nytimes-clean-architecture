package com.nytimes.domain.interactor

import com.nytimes.domain.executor.PostExecutionThread
import com.nytimes.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, in Params>(
    private val executorThread: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    abstract fun buildObservable(params: Params? = null): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = buildObservable(params)
            .subscribeOn(Schedulers.from(executorThread))
            .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}