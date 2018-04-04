package com.nytimes.presentation.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nytimes.R
import com.nytimes.databinding.ActivityStoryDetailBinding
import com.nytimes.presentation.presenter.BaseActivity
import com.nytimes.presentation.model.StoryViewModel

class StoryDetailActivity : BaseActivity<StoryDetailContract.View, StoryDetailContract.Presenter>(),
    StoryDetailContract.View {

    companion object {
        const val STORY = "story"
    }

    override var presenter: StoryDetailContract.Presenter =
        StoryDetailPresenter()
    private lateinit var binding: ActivityStoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_story_detail)

        setSupportActionBar(binding.toolbar)

        val story = intent.getParcelableExtra<StoryViewModel>(STORY)

        supportActionBar?.title = story.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showStory(story)
    }

    override fun showStory(story: StoryViewModel) {
        binding.storyViewModel = story
    }
}