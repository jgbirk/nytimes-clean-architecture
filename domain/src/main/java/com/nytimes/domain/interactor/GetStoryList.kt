package com.nytimes.domain.interactor

import com.nytimes.domain.executor.PostExecutionThread
import com.nytimes.domain.executor.ThreadExecutor
import com.nytimes.domain.model.Story
import com.nytimes.domain.repository.Repository
import io.reactivex.Observable

class GetStoryList(
    private val repository: Repository<Story>,
    val executorThread: ThreadExecutor,
    val postExecutionThread: PostExecutionThread) : UseCase<List<Story>, Void>(executorThread, postExecutionThread) {

    override fun buildObservable(params: Void?): Observable<List<Story>> =
        repository.list()
}