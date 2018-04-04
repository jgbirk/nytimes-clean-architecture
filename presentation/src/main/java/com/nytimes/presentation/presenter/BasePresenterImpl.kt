package com.nytimes.presentation.presenter

import android.support.annotation.VisibleForTesting

open class BasePresenterImpl<V : BaseView> :
    BasePresenter<V> {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}