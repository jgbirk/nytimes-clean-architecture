package com.nytimes.presentation.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseAdapter(private val context: Context) : RecyclerView.Adapter<BindingHolder>() {

    abstract fun getLayoutId(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(context),
            getLayoutId(),
            parent,
            false)

        return BindingHolder(viewDataBinding)
    }
}