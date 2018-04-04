package com.nytimes.presentation.presenter

interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}
