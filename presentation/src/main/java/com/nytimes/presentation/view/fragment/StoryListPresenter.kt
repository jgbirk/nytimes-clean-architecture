package com.nytimes.presentation.view.fragment

import com.nytimes.data.cache.StoryCache
import com.nytimes.data.model.mapper.StoryMapper
import com.nytimes.data.repository.StoryDataSourceFactory
import com.nytimes.data.repository.StoryRepository
import com.nytimes.domain.interactor.DefaultObserver
import com.nytimes.domain.interactor.GetStoryList
import com.nytimes.domain.model.Story
import com.nytimes.presentation.model.mapper.StoryViewModelMapper
import com.nytimes.presentation.presenter.BasePresenterImpl
import com.nytimes.presentation.executor.JobExecutor
import com.nytimes.presentation.executor.UiThread
import io.reactivex.schedulers.Schedulers

class StoryListPresenter(
    cache: StoryCache? = null,
    repository: StoryRepository? = null) : BasePresenterImpl<StoryListContract.View>(), StoryListContract.Presenter {

    private val cache by lazy {
        cache?.let {
            it
        } ?: StoryCache(view!!.getContext())
    }

    private val repository by lazy {
        repository?.let {
            repository
        } ?: StoryRepository(StoryDataSourceFactory(this@StoryListPresenter.cache), StoryMapper)
    }

    private val useCase by lazy {
        GetStoryList(this@StoryListPresenter.repository,
            JobExecutor,
            UiThread
        )
    }

    override fun loadStories(invalidateCache: Boolean) {
        view?.showLoading()

        if (invalidateCache) {
            useCase.addDisposable(
                cache.invalidate()
                    .observeOn(useCase.postExecutionThread.scheduler)
                    .subscribeOn(Schedulers.from(useCase.executorThread))
                .subscribe {
                    useCase.execute(StoryListObserver())
                }
            )
        } else {
            useCase.execute(StoryListObserver())
        }
    }

    override fun detachView() {
        super.detachView()

        useCase.dispose()
    }

    private inner class StoryListObserver : DefaultObserver<List<Story>>() {

        override fun onComplete() {
            view?.hideLoading()
        }

        override fun onError(throwable: Throwable) {
            view?.hideLoading()
            view?.showError(throwable.localizedMessage)

            throwable.printStackTrace()
        }

        override fun onNext(t: List<Story>) {
            view?.showStories(StoryViewModelMapper.transform(t))
        }
    }
}