package com.nytimes.presentation.view.adapter

import android.content.Context
import com.nytimes.BR
import com.nytimes.R
import com.nytimes.presentation.model.StoryViewModel

class StoryAdapter(context: Context) : BaseAdapter(context) {

    var stories: List<StoryViewModel> = listOf()

    override fun getLayoutId(): Int = R.layout.list_item_story

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        with(holder.viewDataBinding) {
            setVariable(BR.storyViewModel, stories[position])
            executePendingBindings()
        }
    }

    override fun getItemCount() = stories.size
}