package com.nytimes.presentation.view.activity

import com.nytimes.presentation.presenter.BasePresenter
import com.nytimes.presentation.presenter.BaseView

object MainContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View>
}