package com.nytimes.presentation.view.fragment

import com.nytimes.presentation.presenter.BasePresenter
import com.nytimes.presentation.model.StoryViewModel
import com.nytimes.presentation.presenter.LoadDataView

object StoryListContract {
    interface View : LoadDataView {
        fun showStories(stories: List<StoryViewModel>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadStories(invalidateCache: Boolean = false)
    }
}