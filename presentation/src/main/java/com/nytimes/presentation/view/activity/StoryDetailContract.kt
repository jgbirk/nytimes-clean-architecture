package com.nytimes.presentation.view.activity

import com.nytimes.presentation.model.StoryViewModel
import com.nytimes.presentation.presenter.BasePresenter
import com.nytimes.presentation.presenter.BaseView

object StoryDetailContract {

    interface View : BaseView {
        fun showStory(story: StoryViewModel)
    }

    interface Presenter : BasePresenter<View>
}