package com.nytimes.presentation.presenter

interface LoadDataView: BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
}