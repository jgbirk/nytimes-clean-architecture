package com.nytimes.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.nytimes.presentation.model.StoryViewModel
import com.nytimes.presentation.view.activity.StoryDetailActivity

object Navigator {

    fun navigateTo(pair: Pair<Context, Intent>, bundle: Bundle? = null) {
        pair.first.startActivity(pair.second, bundle)
    }

    fun storyDetailActivity(context: Context, story: StoryViewModel): Pair<Context, Intent> {
        return context to Intent(context, StoryDetailActivity::class.java).apply {
            putExtra(StoryDetailActivity.STORY, story)
        }
    }

    fun webUrl(context: Context, url: String): Pair<Context, Intent> {
        return context to Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}