package com.nytimes.presentation.presenter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<in V : BaseView, T : BasePresenter<V>> : Fragment(),
    BaseView {

    protected abstract var presenter: T

    override fun getContext(): Context = super.getContext()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }
}