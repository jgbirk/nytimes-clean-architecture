package com.nytimes.presentation.executor

import com.nytimes.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

object UiThread : PostExecutionThread {
    override val scheduler: Scheduler by lazy { AndroidSchedulers.mainThread() }
}