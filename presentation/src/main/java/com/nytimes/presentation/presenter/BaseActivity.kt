package com.nytimes.presentation.presenter

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<in V : BaseView, T : BasePresenter<V>> : AppCompatActivity(),
    BaseView {

    protected abstract var presenter: T

    override fun getContext(): Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition()
                } else {
                    finish()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}