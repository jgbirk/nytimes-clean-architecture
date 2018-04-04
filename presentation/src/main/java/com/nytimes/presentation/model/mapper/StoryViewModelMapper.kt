package com.nytimes.presentation.model.mapper

import com.nytimes.domain.model.Story
import com.nytimes.presentation.model.StoryViewModel

object StoryViewModelMapper {

    fun transform(story: Story) =
        StoryViewModel(story.title, story.summary, story.url, story.publishedDate, story.urlToImage)

    fun transform(stories: List<Story>): List<StoryViewModel> {
        val viewModels = ArrayList<StoryViewModel>()

        stories.forEach {
            viewModels.add(transform(it))
        }

        return viewModels
    }
}